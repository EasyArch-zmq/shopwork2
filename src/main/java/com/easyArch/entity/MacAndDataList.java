package com.easyArch.entity;


import java.io.Serializable;
import java.util.List;


public class MacAndDataList implements Serializable {
    private String mac_address;

    public String getMac_address() {
        return mac_address;
    }

    public void setMac_address(String mac_address) {
        this.mac_address = mac_address;
    }

    public List<DateAndNumber> getList() {
        return list;
    }

    public void setList(List<DateAndNumber> list) {
        this.list = list;
    }

    private List<DateAndNumber>list;
}
