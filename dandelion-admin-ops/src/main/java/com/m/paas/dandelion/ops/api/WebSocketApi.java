package com.m.paas.dandelion.ops.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.m.paas.dandelion.ops.vo.WebSocketResult;

@RestController
@RequestMapping("/api/ops/socket")
public class WebSocketApi {

	@GetMapping("/sendMessage")
	public void sendMessage(@RequestParam String sessionId, @RequestParam String msg) {
		WebSocketServer.sendMessage(WebSocketResult.success("SOCKET_MSG", msg), sessionId);
	}

	@GetMapping("/sendMessage2All")
	public void sendMessage2All(@RequestParam String msg) {
		WebSocketServer.sendMessage2All(WebSocketResult.success("SOCKET_MSG", msg));
	}
}
