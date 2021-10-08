package com.websocket.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import java.util.HashMap;

import static com.websocket.myconst.SystemConst.reservationCount;

@ServerEndpoint(value = "/websocket/{userId}")
@Component
public class WebSocketEndPoint {

    private Session session;

    private static int onlineCount = 0;

    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        this.session = session;
        System.out.println(session.getId());
        if (SessionPool.sessions.containsKey(userId)) {
            SessionPool.sessions.remove(userId);
            SessionPool.sessions.put(userId, session);
        } else {
            SessionPool.sessions.put(userId, session);
            addOnlineCount();
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status","0");
        String string = JSONObject.toJSONString(jsonObject);

        onMessage(string);
    }

    @OnClose
    public void onClose(Session session) {
        try {
            SessionPool.close(session.getId());
            session.close();
            subOnlineCount();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnMessage
    public void onMessage(String message) {

        JSONObject data = JSONObject.parseObject(message);

        System.out.println(data.toString());

        String status = data.get("status").toString();
        if (status.equalsIgnoreCase("0")){

            HashMap<String, Object> map = new HashMap<>();
            map.put("currentReservationCount",reservationCount);
            String string = JSONObject.toJSONString(map);
            SessionPool.sendMessage(string);
        }

        if (status.equalsIgnoreCase("1")){
            reservationCount++;
            String patient_id = data.get("patient_id").toString();
            HashMap<String, Object> map = new HashMap<>();
            map.put("currentPosition",reservationCount);
            map.put("patient_id",patient_id);
            map.put("currentReservationCount",reservationCount);
            String string = JSONObject.toJSONString(map);
            SessionPool.sendMessage(string);
        }

        if (status.equalsIgnoreCase("2")){

        }



       /* HashMap<String, Object> map = new HashMap<>();
        map.put("Current",reservationCount);
        map.put("patient_id",message);

        SessionPool.sendMessage(map.toString());*/
    }


    public void addOnlineCount() {
        onlineCount++;
        System.out.println(onlineCount);
    }

    public void subOnlineCount() {
        onlineCount--;
        System.out.println(onlineCount);
    }
}
