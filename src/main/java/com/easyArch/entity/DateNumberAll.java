package com.easyArch.entity;

import lombok.Data;

import java.util.List;

@Data
public class DateNumberAll {
    private String address;
    private String mac_address;
    private String time1;
    private String time2;
    private List<DateAndNumber>list;
}
