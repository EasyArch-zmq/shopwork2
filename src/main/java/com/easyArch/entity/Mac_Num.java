package com.easyArch.entity;

import java.io.Serializable;

public class Mac_Num implements Serializable {
    private String mac_address;
    private Integer num;

    public String getMac_address() {
        return mac_address;
    }

    public void setMac_address(String mac_address) {
        this.mac_address = mac_address;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
