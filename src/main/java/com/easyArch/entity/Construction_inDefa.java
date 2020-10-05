package com.easyArch.entity;




import java.util.List;


public class Construction_inDefa {
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

    public List<Info_inCons> getInfo_inCons_List() {
        return info_inCons_List;
    }

    public void setInfo_inCons_List(List<Info_inCons> info_inCons_List) {
        this.info_inCons_List = info_inCons_List;
    }

    private String picture_url;
    List<Info_inCons>  info_inCons_List;


}
