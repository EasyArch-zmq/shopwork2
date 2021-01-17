package com.easyArch.entity;

import java.io.Serializable;

public class YanGan implements Serializable {
    private Integer yanGan;
    private String time;

    public Integer getYanGan() {
        return yanGan;
    }

    public void setYanGan(Integer yanGan) {
        this.yanGan = yanGan;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
