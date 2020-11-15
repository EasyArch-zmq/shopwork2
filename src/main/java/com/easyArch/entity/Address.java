package com.easyArch.entity;


public class Address {
    private String city;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getSpecial_address() {
        return special_address;
    }

    public void setSpecial_address(String special_address) {
        this.special_address = special_address;
    }

    private String county;
    private String street;
    private String special_address;//在别处有引用，不能删
    private String mac_address;
    private String location;
    private String specific_address;//西瓜图里引用了

    public String getSpecific_address() {
        return specific_address;
    }

    public void setSpecific_address(String specific_address) {
        this.specific_address = specific_address;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMac_address() {
        return mac_address;
    }

    public void setMac_address(String mac_address) {
        this.mac_address = mac_address;
    }
}
