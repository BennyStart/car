package com.ben.controller.socket;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

public class MyHandShakeInterceptor implements HandshakeInterceptor {

	/**
	 * 初次握手访问前
	 */
	@Override
	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler hander,
			Map<String, Object> map) throws Exception {
		if (request instanceof ServletServerHttpRequest) {
			HttpServletRequest servletRequest = ((ServletServerHttpRequest) request).getServletRequest();
			// 使用userName区分WebSocketHandler，以便定向发送消息
			String username = (String) servletRequest.getSession().getAttribute("username");
			if (username != null) {
				// 为服务器创建WebSocketSession做准备
				map.put("username", username);
				return true;
			} else {
				return false;
			}

		}
		return true;
	}

	@Override
	public void afterHandshake(ServerHttpRequest arg0, ServerHttpResponse arg1, WebSocketHandler arg2, Exception arg3) {
		// TODO Auto-generated method stub

	}

}
