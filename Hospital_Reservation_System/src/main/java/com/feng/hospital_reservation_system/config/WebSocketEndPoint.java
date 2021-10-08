package com.feng.hospital_reservation_system.config;

import com.alibaba.fastjson.JSONObject;
import com.feng.hospital_reservation_system.pojo.Reservation;
import com.feng.hospital_reservation_system.service.ReservationService;
import com.feng.hospital_reservation_system.utils.MyJsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.feng.hospital_reservation_system.myconst.SystemConst.*;

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
        onMessage("");
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

        Map<String, Object> map = new HashMap<>();
        map.put("currentReservationCount", RESERVATIONS.size());
        map.put("currentIndex", CURRENT_RESERVATION_INDEX);
        map.put("currentDoctorCount",ONLINEDOCTORCOUNT);
        SessionPool.sendMessage(JSONObject.toJSONString(map));
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
