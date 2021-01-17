package com.easyArch.entity;

import java.io.Serializable;

/**
 * 年，月，地址
 */
public class Address_Month implements Serializable {
    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    private String year;
    private String address;
    private String month;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
