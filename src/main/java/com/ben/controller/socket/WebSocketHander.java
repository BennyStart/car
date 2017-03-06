package com.ben.controller.socket;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

public class WebSocketHander implements WebSocketHandler {

	private static final Map<String, WebSocketSession> sessionUserMap = new HashMap<String, WebSocketSession>();

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		System.out.println("链接关闭");
		sessionUserMap.remove((String) session.getHandshakeAttributes().get("username"));
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println("链接成功");
		String key = (String) session.getHandshakeAttributes().get("username");
		if (sessionUserMap.get(key) == null) {
			sessionUserMap.put(key, session);
		}
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> webSocketMessage) throws Exception {
		System.out.println("接收处理消息");
		// webSocketMessage.getPayload() 可以用Gson转成对象
		sendMessageToUser((String) session.getHandshakeAttributes().get("username"),
				new TextMessage(webSocketMessage.getPayload() + ""));

	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable throwable) throws Exception {
		System.out.println("链接错误");
		if (session.isOpen()) {
			session.close();
		}
		sessionUserMap.remove((String) session.getHandshakeAttributes().get("username"));

	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}

	/**
	 * 给某个用户发送消息
	 *
	 * @param userName
	 * @param message
	 */
	public void sendMessageToUser(String userName, TextMessage message) {
		for (Entry<String, WebSocketSession> entry : sessionUserMap.entrySet()) {
			WebSocketSession user = entry.getValue();
			try {
				if (user.isOpen()) {
					user.sendMessage(message);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
