package com.easyArch.entity;

import lombok.Data;

import java.util.List;
@Data
public class Info_inCons {
    private String tier;
    private String mac_address;
    private String location;
    private List<DateAndNumber> list_inCons;
}
