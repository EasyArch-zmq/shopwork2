package com.easyArch.entity;


import java.io.Serializable;

public class DateAndNumber implements Serializable {
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    private String time;
    private int num;
}
