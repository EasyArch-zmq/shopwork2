package com.easyArch.entity;


import java.io.Serializable;

/**
 * 此数据放入
 */

    public class Location_tier implements Serializable {
    private String location;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTier() {
        return tier;
    }

    public void setTier(String tier) {
        this.tier = tier;
    }

    private String tier;
}
