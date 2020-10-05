package com.easyArch.entity;


public class Data_inCons {
    private String mac_address;

    public String getMac_address() {
        return mac_address;
    }

    public void setMac_address(String mac_address) {
        this.mac_address = mac_address;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Color1 getColor() {
        return color;
    }

    public void setColor(Color1 color) {
        this.color = color;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    private Integer number;
    private Color1 color;
    private String location;
}
