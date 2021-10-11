package com.websocket.config;

import com.alibaba.fastjson.JSON;
import com.websocket.model.Message;
import com.websocket.model.User;

import javax.websocket.Session;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class SessionPool {

    public static Map<String, Session> sessions = new ConcurrentHashMap<>();
    public static List<User> onlineUsers = Collections.synchronizedList(new ArrayList<>());

    public static void open(Session session, String user_id) {
        if (sessions.containsKey(user_id)) {
            sessions.remove(user_id);
            sessions.put(user_id, session);
        } else {
            sessions.put(user_id, session);
            addOnlineUser(user_id);
        }
        Message message = new Message(user_id, 0, new Date(), user_id + "加入了群聊", "", onlineUsers);
        System.out.println(message);
        sendMessage(JSON.toJSONString(message));
    }

    public static void close(String sessionId) throws Exception {
        String user_id = null;
        for (String userId : sessions.keySet()) {
            Session session = sessions.get(userId);
            if (sessionId.equals(session.getId())) {
                user_id = userId;
                sessions.remove(userId);
                break;
            }
        }

        subOnlineUser(user_id);
        Message message = new Message(user_id, 0, new Date(), user_id + "退出了群聊", "", onlineUsers);
        System.out.println(message);
        sendMessage(JSON.toJSONString(message));
    }

    public static void sendMessage(String sessionId, String message) {
        sessions.get(sessionId).getAsyncRemote().sendText(message);
    }

    public static void sendMessage(String message) {

        for (String sessionId : sessions.keySet()) {
            sessions.get(sessionId).getAsyncRemote().sendText(message);
        }
    }

    private static synchronized void addOnlineUser(String user_id) {
        onlineUsers.add(new User(user_id, ""));
    }

    public static synchronized void subOnlineUser(String user_id) {
        for (User onlineUser : onlineUsers) {
            if (onlineUser.getUser_id().equals(user_id)) {
                onlineUsers.remove(onlineUser);
                break;
            }
        }
    }
}
