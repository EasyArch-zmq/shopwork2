package com.easyArch.entity;

import lombok.Data;

import java.util.List;

@Data
public class MacAndDataList {
    private String mac_address;
    private List<DateAndNumber>list;
}
