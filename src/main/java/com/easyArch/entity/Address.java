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
    private String special_address;
}
