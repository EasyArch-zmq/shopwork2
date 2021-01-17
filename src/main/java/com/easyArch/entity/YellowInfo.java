package com.easyArch.entity;

import java.io.Serializable;

public class YellowInfo implements Serializable {
    private String time;
    private String mac_address;
    private Integer num;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


    public String getMac_address() {
        return mac_address;
    }

    public void setMac_address(String mac_address) {
        this.mac_address = mac_address;
    }

    public Integer getNumber() {
        return num;
    }

    public void setNumber(Integer num) {
        this.num = num;
    }
}
