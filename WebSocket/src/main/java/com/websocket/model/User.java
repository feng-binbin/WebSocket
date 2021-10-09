package com.websocket.model;

public class User {
    private String user_id;
    private String username;

    public User() {
    }

    public User(String user_id, String username) {
        this.user_id = user_id;
        this.username = username;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id='" + user_id + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
