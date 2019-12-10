package com.m.paas.dandelion.ops.helper;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.jcraft.jsch.JSchException;
import com.m.paas.dandelion.ops.api.WebSocketServer;
import com.m.paas.dandelion.ops.ssh.LinuxSSHTerminal;
import com.m.paas.dandelion.ops.vo.WebSocketResult;

public class WebsocketShellHepler {

	private static final Map<String, LinuxSSHTerminal> webSocketTerminalMap = new ConcurrentHashMap<>();

	static {
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			webSocketTerminalMap.values().forEach(LinuxSSHTerminal::close);
		}));
	}

	public static void initWebTerminal(String websocketSessionId, String host, String username, String password)
			throws IOException, JSchException {
		if (!webSocketTerminalMap.containsKey(websocketSessionId)) {
			LinuxSSHTerminal linuxSSHTerminal = new LinuxSSHTerminal(host, username, password, websocketSessionId);
			webSocketTerminalMap.put(websocketSessionId, linuxSSHTerminal);
			linuxSSHTerminal.open();
			WebSocketServer.sendMessage(
					WebSocketResult.success(TerminalConstant.T_TERMINAL_LOGIN, "Linux Connected! \r\n"),
					websocketSessionId);
		}
	}

	public static void sendCommand(String websocketSessionId, String cmd) throws IOException, JSchException {
		if (webSocketTerminalMap.containsKey(websocketSessionId)) {
			LinuxSSHTerminal linuxSSHTerminal = webSocketTerminalMap.get(websocketSessionId);
			linuxSSHTerminal.sendCommand(cmd);
		} else {
			WebSocketServer.sendMessage(WebSocketResult.error("terminalCommand", "not login SSH"), websocketSessionId);
		}
	}

	public static void closeWebTerminal(String websocketSessionId) {
		if (webSocketTerminalMap.containsKey(websocketSessionId)) {
			LinuxSSHTerminal linuxSSHTerminal = webSocketTerminalMap.remove(websocketSessionId);
			linuxSSHTerminal.close();
			WebSocketServer.sendMessage(WebSocketResult.success("terminalClosed", "closed"), websocketSessionId);
		}
	}
}
