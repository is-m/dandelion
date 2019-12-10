package com.m.paas.dandelion.ops.ssh;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.TimeUnit;

import org.springframework.util.StreamUtils;

import com.jcraft.jsch.ChannelShell;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.m.paas.dandelion.ops.api.WebSocketServer;
import com.m.paas.dandelion.ops.vo.WebSocketResult;

import ch.qos.logback.core.util.TimeUtil;

public class LinuxSSHTerminal implements Closeable {
	
	private String host;
	private String username;
	private String passwrod;
	private String websocketSessionId;
	private int port = 22;

	private Session session;
	private ChannelShell channelShell;

	private InputStream inputStream = null;
	private OutputStream outputStream = null;
	
	
	public LinuxSSHTerminal(String host, String username, String password, String websocketSessionId) {
		this.host = host;
		this.username = username;
		this.passwrod = password;
		this.websocketSessionId = websocketSessionId;
	}

	/**
	 * 连接
	 * 
	 * @throws JSchException
	 * @throws IOException
	 */
	public void open() throws JSchException, IOException {
		if (session == null || !session.isConnected()) {
			JSch jSch = new JSch();
			session = jSch.getSession(this.username, this.host, port);
			session.setPassword(this.passwrod);
			session.setUserInfo(new MyUserInfo());
			session.connect();

			channelShell = (ChannelShell) session.openChannel("shell");
			channelShell.connect(3000);
			inputStream = channelShell.getInputStream();
			outputStream = channelShell.getOutputStream();
			this.initLinuxResponseListener();
		}
	}

	private void initLinuxResponseListener() {
		Thread listener = new Thread(() -> {
			byte[] tmp = new byte[1024];
			StringBuilder resultBuilder = new StringBuilder();
			
			while (true) {
				try {
					TimeUnit.MILLISECONDS.sleep(100);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				try {
					
					
					while (inputStream.available() > 0) {
						int i = inputStream.read(tmp, 0, 1024);
						if (i < 0) {
							break;
						}
						String shellOutSting = new String(tmp, 0, i);
						System.out.println(shellOutSting);
						resultBuilder.append(shellOutSting);
						TimeUnit.MILLISECONDS.sleep(20);
					}
					if (resultBuilder.length() > 0) {
						WebSocketServer.sendMessage(
								WebSocketResult.success("terminalCommand", resultBuilder.toString()),
								this.websocketSessionId);
						resultBuilder.setLength(0);
					}
				} catch (IOException e) {
					e.printStackTrace();
					break;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		listener.start();
	}

	/**
	 * 执行命令
	 * 
	 * @throws IOException
	 * @throws JSchException
	 * @throws InterruptedException
	 */
	public void sendCommand(String... cmds) throws IOException, JSchException {
		// https://www.dazhuanlan.com/2019/09/28/5d8e4f2219ba4/
		if (session == null || !session.isConnected()) {
			this.open();
		}

		for (String cmd : cmds) {
			outputStream.write(cmd.getBytes());
			outputStream.flush();
		}
	}

	/**
	 * 关闭连接
	 */
	@Override
	public void close() {
		if (session != null && session.isConnected()) {
			session.disconnect();
		}
	}
}
