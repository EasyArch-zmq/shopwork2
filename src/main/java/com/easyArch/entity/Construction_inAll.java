package com.easyArch.entity;

import lombok.Data;

import java.util.List;

/**
 * 返回以建筑展开的数据
 */
@Data
public class Construction_inAll {
    private String construction;
    private String picture_url;
    private List<Data_inCons> list_inCons;
}
