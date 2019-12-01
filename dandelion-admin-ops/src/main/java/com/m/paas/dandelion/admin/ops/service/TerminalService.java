/**
 * https://github.com/javaterminal/cloudterm
 */
package com.m.paas.dandelion.admin.ops.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.LinkedBlockingQueue;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.m.paas.dandelion.admin.ops.api.WebSocketServer;
import com.m.paas.dandelion.admin.ops.helper.IOHelper;
import com.m.paas.dandelion.admin.ops.helper.ThreadHelper;
import com.pty4j.PtyProcess;
import com.pty4j.WinSize;

@Service
@Scope("prototype")
public class TerminalService {

	@Value("${shell:#{null}}")
	private String shellStarter;

	private boolean isReady;
	private String[] termCommand;
	private PtyProcess process;
	private Integer columns = 20;
	private Integer rows = 10;
	private BufferedReader inputReader;
	private BufferedReader errorReader;
	private BufferedWriter outputWriter;
	private WebSocketSession webSocketSession;

	private LinkedBlockingQueue<String> commandQueue = new LinkedBlockingQueue<>();

	public void onTerminalInit() {

	}

	public void onTerminalReady() {

		ThreadHelper.start(() -> {
			isReady = true;
			try {
				initializeProcess();
			} catch (Exception e) {
				// e.printStackTrace();
			}
		});

	}
	
	private boolean isWindows() {
		return System.getProperty("os.name").toLowerCase().indexOf("windows") >= 0;
	}

	private void initializeProcess() throws Exception {

		String userHome = System.getProperty("user.home");
		Path dataDir = Paths.get(userHome).resolve(".terminalfx");
		IOHelper.copyLibPty(dataDir);

		if (isWindows()) {
			this.termCommand = "cmd.exe".split("\\s+");
		} else {
			this.termCommand = "/bin/bash -i".split("\\s+");
		}

		if (Objects.nonNull(shellStarter)) {
			this.termCommand = shellStarter.split("\\s+");
		}

		Map<String, String> envs = new HashMap<>(System.getenv());
		envs.put("TERM", "xterm");

		System.setProperty("PTY_LIB_FOLDER", dataDir.resolve("libpty").toString());

		this.process = PtyProcess.exec(termCommand, envs, userHome);

		process.setWinSize(new WinSize(columns, rows));
		this.inputReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		this.errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
		this.outputWriter = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));

		ThreadHelper.start(() -> {
			printReader(inputReader);
		});

		ThreadHelper.start(() -> {
			printReader(errorReader);
		});

		process.waitFor();

	}

	public void print(String text) throws IOException {

		Map<String, String> map = new HashMap<>();
		map.put("type", "TERMINAL_PRINT");
		map.put("text", text);

		String message = new ObjectMapper().writeValueAsString(map);
		//WebSocketServer.sendMessage2All(message); 

	}

	private void printReader(BufferedReader bufferedReader) {
		try {
			int nRead;
			char[] data = new char[1 * 1024];

			while ((nRead = bufferedReader.read(data, 0, data.length)) != -1) {
				StringBuilder builder = new StringBuilder(nRead);
				builder.append(data, 0, nRead);
				print(builder.toString());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onCommand(String sessionId,String command) throws InterruptedException {

		if (Objects.isNull(command)) {
			return;
		}

		commandQueue.put(command);
		ThreadHelper.start(() -> {
			try {
				outputWriter.write(commandQueue.poll());
				outputWriter.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		});

	}

	public void onTerminalResize(String columns, String rows) {
		if (Objects.nonNull(columns) && Objects.nonNull(rows)) {
			this.columns = Integer.valueOf(columns);
			this.rows = Integer.valueOf(rows);

			if (Objects.nonNull(process)) {
				process.setWinSize(new WinSize(this.columns, this.rows));
			}

		}
	}

	public void setWebSocketSession(WebSocketSession webSocketSession) {
		this.webSocketSession = webSocketSession;
	}

	public WebSocketSession getWebSocketSession() {
		return webSocketSession;
	}
}
