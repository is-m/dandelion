package com.m.paas.dandelion.admin.ops.api;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.m.paas.dandelion.admin.ops.helper.TerminalConstant;
import com.m.paas.dandelion.admin.ops.helper.WebsocketShellHepler;
import com.m.paas.dandelion.admin.ops.vo.WebSocketResult;

@Component
@ServerEndpoint("/server/ops/socket")
public class WebSocketServer {

	private static final Logger log = LoggerFactory.getLogger(WebSocketServer.class);

	private static final List<Session> SESSIONS = new CopyOnWriteArrayList<Session>();

	@OnOpen
	public void handleOpen(Session session) {
		SESSIONS.add(session);
		sendMessage(WebSocketResult.success("SOCKET_CONNECT", "connected"), session);
	}

	@OnClose
	public void handleClose(Session session) {
		WebsocketShellHepler.closeWebTerminal(session.getId());
		SESSIONS.remove(session);
	}

	@OnError
	public void handleError(Session session, Throwable e) {
		log.error("web socket error with sessionId is " + session.getId(), e);
	}

	@OnMessage
	public void handleMessage(String message, Session session) {
		log.info("reseive message for client {}", message);
		Map<String, String> messageMap = getMessageMap(message);
		if (messageMap.containsKey("type")) {
			String type = messageMap.get("type");

			switch (type) {
			case TerminalConstant.T_TERMINAL_LOGIN:
				try {
					WebsocketShellHepler.initWebTerminal(session.getId(), messageMap.get("host"),
							messageMap.get("username"), messageMap.get("password"));
				} catch (Exception e) {
					WebsocketShellHepler.closeWebTerminal(session.getId());
					sendMessage(WebSocketResult.error(TerminalConstant.T_TERMINAL_LOGIN, e.getMessage()), session);
					e.printStackTrace();
				}
				break;
			case TerminalConstant.T_TERMINAL_CMD:
				try {
					WebsocketShellHepler.sendCommand(session.getId(), messageMap.get("command"));
				} catch (Exception e) {
					WebsocketShellHepler.closeWebTerminal(session.getId());
					sendMessage(WebSocketResult.error(TerminalConstant.T_TERMINAL_LOGIN, e.getMessage()), session);
					e.printStackTrace();
				}
				break;
			default:
				throw new RuntimeException("Unrecodnized action");
			}
		}

	}

	private Map<String, String> getMessageMap(String message) {
		try {
			Map<String, String> map = new ObjectMapper().readValue(message, new TypeReference<Map<String, String>>() {
			});
			return map;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new HashMap<>();
	}

	private static ObjectMapper om = new ObjectMapper();

	public static void sendMessage(WebSocketResult message, Session session) {
		try {
			if (session.isOpen()) {
				String json = om.writeValueAsString(message);
				log.info(json);
				session.getBasicRemote().sendText(json);
			} else {
				log.warn("session {} is closed ", session.getId());
			}
		} catch (IOException e) {
			log.error("websocket send message error", e);
		}
	}

	public static void sendMessage(WebSocketResult message, String sessionId) {
		Session session = null;
		for (Session s : SESSIONS) {
			if (s.getId().equals(sessionId)) {
				session = s;
				break;
			}
		}

		if (session != null) {
			sendMessage(message, session);
		} else {
			log.warn("not found session by id {}", sessionId);
		}
	}

	public static void sendMessage2All(WebSocketResult message) {
		for (Session session : SESSIONS) {
			if (session.isOpen()) {
				sendMessage(message, session);
			}
		}
	}

}
