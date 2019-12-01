const style = {};

const currentGeometry = () => {
  if (!style.width || !style.height) {
    const text = $('.xterm-helpers style').text();
    let arr = text.split('xterm-normal-char{width:');
    style.width = parseFloat(arr[1]);
    arr = text.split('div{height:');
    style.height = parseFloat(arr[1]);
  }
  const columns = parseInt(window.innerWidth / style.width, 10) - 1;
  const rows = parseInt(window.innerHeight / style.height, 10);
  return { columns, rows };
};

const action = (type, data) =>
  JSON.stringify({
    type,
    ...data,
  });

const resizeTerm = (term, ws) => {
  const { columns, rows } = currentGeometry();
  if (columns !== term.geometry[0] || rows !== term.geometry[1]) {
    console.log(`resizing term to ${JSON.stringify({ columns, rows })}`);
    term.resize(columns, rows);
    ws.send(
      action('TERMINAL_RESIZE', {
        columns,
        rows,
      })
    );
  }
};

$(window).resize(function() {
  resizeTerm(term, ws);
});

$(() => {
  let ws = new WebSocket('ws://' + location.host + '/server/ops/socket');
  let term = new Terminal({
    cursorBlink: true,
  });

  term.on('data', command => {
    console.log(command);
    ws.send(
      action('TERMINAL_COMMAND', {
        command,
      })
    );
  });

  ws.onopen = () => {
    ws.send(action('TERMINAL_INIT'));
    ws.send(action('TERMINAL_READY'));
    term.open(document.getElementById('#terminal'), true);
    term.toggleFullscreen(true);
  };

  ws.onmessage = e => {
    if (!term.resized) {
      resizeTerm(term, ws);
      term.resized = true;
    }
    let data = JSON.parse(e.data);
    switch (data.type) {
      case 'TERMINAL_PRINT':
        term.write(data.text);
    }
  };

  ws.onerror = e => {
    console.log(e);
  };

  ws.onclose = e => {
    console.log(e);
    term.destroy();
  };
});
