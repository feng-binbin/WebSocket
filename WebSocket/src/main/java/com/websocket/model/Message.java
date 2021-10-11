package com.websocket.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Message {
    private String username;
    private int type;
    private Date date;
    private String date_str;
    private String info;
    private String receiver;
    private List<User> list;

    public Message() {
    }

    public Message(String username, int type, Date date, String info, String receiver, List<User> list) {
        this.username = username;
        this.type = type;
        this.date = date;
        this.info = info;
        this.receiver = receiver;
        this.list = list;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDate_str() {
        if (date != null) {
            date_str = new SimpleDateFormat("HH:mm:ss").format(date);
        }
        return date_str;
    }

    public void setDate_str(String date_str) {
        this.date_str = date_str;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public List<User> getList() {
        return list;
    }

    public void setList(List<User> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "Message{" +
                "username='" + username + '\'' +
                ", type=" + type +
                ", date=" + date +
                ", info='" + info + '\'' +
                ", receiver='" + receiver + '\'' +
                ", list=" + list +
                '}';
    }
}
