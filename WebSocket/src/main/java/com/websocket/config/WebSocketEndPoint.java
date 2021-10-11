package com.websocket.config;

import com.alibaba.fastjson.JSON;
import com.websocket.model.Message;
import com.websocket.model.User;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@ServerEndpoint(value = "/websocket/{userId}")
@Component
public class WebSocketEndPoint {

    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        SessionPool.open(session, userId);
    }

    @OnClose
    public void onClose(Session session) {
        try {
            SessionPool.close(session.getId());
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnMessage
    public void onMessage(String message) {
        Message data = JSON.parseObject(message, Message.class);
        data.setDate(new Date());
        /*群聊*/
        if (data.getType() == 0) {
            SessionPool.sendMessage(JSON.toJSONString(data));
        }

        /*私聊*/
        if (data.getType() == 1) {
            SessionPool.sendMessage(JSON.toJSONString(data));
        }
    }
}
