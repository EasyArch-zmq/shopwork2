package com.easyArch.entity;



import java.util.List;

public class Info_inCons {
    private String tier;
    private String mac_address;
    private String location;

    public String getTier() {
        return tier;
    }

    public void setTier(String tier) {
        this.tier = tier;
    }

    public String getMac_address() {
        return mac_address;
    }

    public void setMac_address(String mac_address) {
        this.mac_address = mac_address;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<DateAndNumber> getList_inCons() {
        return list_inCons;
    }

    public void setList_inCons(List<DateAndNumber> list_inCons) {
        this.list_inCons = list_inCons;
    }

    private List<DateAndNumber> list_inCons;
}
