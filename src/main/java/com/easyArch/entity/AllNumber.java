package com.easyArch.entity;

import lombok.Data;

import java.util.List;
@Data
public class AllNumber {
    private String userAddress;
    private Color color;
    private List<AddressAndNumber> list;
}
