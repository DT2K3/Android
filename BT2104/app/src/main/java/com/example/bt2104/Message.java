package com.example.bt2104;

public class Message {
    private int id;
    private String phone;
    private String body;
    private int type;

    public Message(int id, String phone, String body, int type) {
        this.id = id;
        this.phone = phone;
        this.body = body;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
