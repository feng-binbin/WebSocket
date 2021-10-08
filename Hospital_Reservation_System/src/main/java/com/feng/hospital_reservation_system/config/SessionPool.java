package com.feng.hospital_reservation_system.config;

import javax.websocket.Session;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SessionPool {

    public static Map<String, Session> sessions = new ConcurrentHashMap<>();

    public static void close(String sessionId) throws Exception {
        for (String userId : sessions.keySet()) {
            Session session = sessions.get(userId);
            if (sessionId.equals(session.getId())) {
                sessions.remove(userId);
                break;
            }
        }
    }

    public static void sendMessage(String sessionId, String message) {
        sessions.get(sessionId).getAsyncRemote().sendText(message);
    }

    public static void sendMessage(String message) {

        for (String sessionId : sessions.keySet()) {
            sessions.get(sessionId).getAsyncRemote().sendText(message);
        }

    }
}
