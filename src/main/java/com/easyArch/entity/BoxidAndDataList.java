package com.easyArch.entity;

import lombok.Data;

import java.util.List;

@Data
public class BoxidAndDataList {
    private String boxid;
    private List<DateAndNumber>list;
}
