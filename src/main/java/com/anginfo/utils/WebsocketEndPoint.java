package com.anginfo.utils;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class WebsocketEndPoint extends TextWebSocketHandler {
	private static ArrayList<WebSocketSession> users = null;

	static {
		users = new ArrayList<WebSocketSession>();
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		super.handleTextMessage(session, message);
		//TextMessage returnMessage = new TextMessage(message.getPayload() + " received at server");
		//session.sendMessage(returnMessage);
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		users.add(session);
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		if (session.isOpen()) {
			session.close();
		}
		users.remove(session);
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		users.remove(session);
	}
	/**
	 * Stone.Cai
	 * 2016年08月09日09:28:10
	 * 添加
	 * 发送消息给客户端
	 */
	public static void sendMessageToUsers(TextMessage message) {
        for (WebSocketSession user : users) {
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
