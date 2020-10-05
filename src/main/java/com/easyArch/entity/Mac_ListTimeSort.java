package com.easyArch.entity;

import java.util.List;

public class Mac_ListTimeSort {
    private String mac_address;
    private List<DateAndNumber>list;

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
}
