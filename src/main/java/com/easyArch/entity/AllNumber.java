package com.easyArch.entity;

import lombok.Data;

import java.util.List;

/**
 * 此数据作为返回前端的总数据
 */
@Data
public class AllNumber {
    private String userAddress;
    private List<Construction_inAll> list_inAll;
}
