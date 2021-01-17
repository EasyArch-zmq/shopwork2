package com.easyArch.entity;



import java.io.Serializable;
import java.util.List;

/**
 * 返回以建筑展开的数据
 */

public class Construction_inAll implements Serializable {
    private String construction;

    public String getConstruction() {
        return construction;
    }

    public void setConstruction(String construction) {
        this.construction = construction;
    }

    public String getPicture_url() {
        return picture_url;
    }

    public void setPicture_url(String picture_url) {
        this.picture_url = picture_url;
    }

    public List<Data_inCons> getList_inCons() {
        return list_inCons;
    }

    public void setList_inCons(List<Data_inCons> list_inCons) {
        this.list_inCons = list_inCons;
    }

    private String picture_url;
    private List<Data_inCons> list_inCons;
}
