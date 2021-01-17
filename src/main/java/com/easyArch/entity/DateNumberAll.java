package com.easyArch.entity;



import java.io.Serializable;
import java.util.List;


public class DateNumberAll implements Serializable {
    private String address;
    private String mac_address;
    private String time1;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMac_address() {
        return mac_address;
    }

    public void setMac_address(String mac_address) {
        this.mac_address = mac_address;
    }

    public String getTime1() {
        return time1;
    }

    public void setTime1(String time1) {
        this.time1 = time1;
    }

    public String getTime2() {
        return time2;
    }

    public void setTime2(String time2) {
        this.time2 = time2;
    }

    public List<DateAndNumber> getList() {
        return list;
    }

    public void setList(List<DateAndNumber> list) {
        this.list = list;
    }

    private String time2;
    private List<DateAndNumber>list;
}
