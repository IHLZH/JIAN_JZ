package com.example.jian_jz.Event;

public class BtnTypeEvent {
    private String typeMessage;
    private Integer img;

    public BtnTypeEvent(String typeMessage) {
        this.typeMessage = typeMessage;
    }

    public BtnTypeEvent(String typeMessage, Integer img) {
        this.typeMessage = typeMessage;
        this.img = img;
    }

    public BtnTypeEvent() {
    }

    public String getTypeMessage() {
        return typeMessage;
    }

    public void setTypeMessage(String typeMessage) {
        this.typeMessage = typeMessage;
    }

    public Integer getImg() {
        return img;
    }

    public void setImg(Integer img) {
        this.img = img;
    }
}
