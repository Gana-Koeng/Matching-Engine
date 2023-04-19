package CSX.MatchingEngine.app.websocket.etc;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingDeque;

@Component @Log4j2
public class RawSocketHandler extends TextWebSocketHandler {
	final IpHandshakeInterceptor ipHandshakeInterceptor;

	//list of all sessions used to send real time data to all
	List<WebSocketSession> sessions = new CopyOnWriteArrayList();

	//key -> WebSocketSession where key = USER_ID; used to send user specific data like execution
	Map<String, List<WebSocketSession>> socketSessionMap = new TreeMap<>();

	//sessionId -> key where key = USER_ID; used to remove session when user log out
	Map<String, String> sessionIdToKey = new TreeMap<>();

	BlockingDeque<String> messagesForAll = new LinkedBlockingDeque<>();

	public RawSocketHandler(IpHandshakeInterceptor ipHandshakeInterceptor) {
		this.ipHandshakeInterceptor = ipHandshakeInterceptor;
		startSendToAllThread();
	}

	private void startSendToAllThread() {
		Thread thread = new Thread(this::sendToAllLoop);
		thread.setName("WebSocketSend");
		thread.start();
	}

	private void sendToAllLoop() {
		while (true) {
			try {
				String message = messagesForAll.take();
				sendToAllClients(message);
			} catch (Exception e) {
				log.error(e);
			}
		}
	}


	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		String clientIp = ipHandshakeInterceptor.getClientIp(session);
		log.info("client ip: {}", clientIp);

		sessions.add(session);
		log.info("connected user[{}], total webSocket count[{}]", clientIp, sessions.size());

		String key = clientIp;
		if (socketSessionMap.containsKey(key)) {
			socketSessionMap.get(key).add(session);
		} else {
			socketSessionMap.put(key, new ArrayList<>(Arrays.asList(session)));
		}

		sessionIdToKey.put(session.getId(), key);
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		closeSession(session);
		log.info("Websocket connection closed status[{}] total webSocket count[{}]", status, sessions.size());
	}
	private void closeSession(WebSocketSession session) {
		sessions.remove(session);

		if (!sessionIdToKey.containsKey(session.getId())) return;

		String key = sessionIdToKey.get(session.getId());
		sessionIdToKey.remove(session.getId());

		if (!socketSessionMap.containsKey(key)) return;

		List<WebSocketSession> socketSessions = socketSessionMap.get(key);
		socketSessions.remove(session);

		if (socketSessions.size() == 0) socketSessionMap.remove(key);
	}

	public void sendAll(String msg) {
		messagesForAll.add(msg);
	}

	private void sendToAllClients(String msg) {
		for (WebSocketSession session: sessions) {
			sendMessage(session, msg);
		}
	}


	private void sendMessage(WebSocketSession session, String msg) {
		System.out.println(session.isOpen());
		try {
			if (! session.isOpen()) {
				closeSession(session);
				return;
			}
			session.sendMessage(new TextMessage(msg));
		} catch (IOException e) {
			log.warn(e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

}