package com.feng.hospital_reservation_system.utils;

public class MSG<T> {

    private Integer code;
    private String message;
    private T data;

    public MSG() {
    }

    public static <T> MSG success(T data) {
        MSG msg = new MSG<>();
        msg.setCode(200);
        msg.setMessage("success");
        msg.setData(data);
        return msg;
    }

    public static <T> MSG failed() {
        MSG msg = new MSG<>();
        msg.setCode(500);
        msg.setMessage("failed");
        msg.setData(false);
        return msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public MSG setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "MSG{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
