package com.easyArch.entity;

import java.util.List;

public class ColorAndData {
    private String color;
    private List<YellowInfo> YellowInfoList;
    private List<GreenInfo> GreenInfoList;
    private List<RedInfo> RedInfoList;
    public ColorAndData(String color){
        this.color=color;
    }
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<YellowInfo> getYellowInfoList() {
        return YellowInfoList;
    }

    public void setYellowInfoList(List<YellowInfo> yellowInfoList) {
        YellowInfoList = yellowInfoList;
    }

    public List<GreenInfo> getGreenInfoList() {
        return GreenInfoList;
    }

    public void setGreenInfoList(List<GreenInfo> greenInfoList) {
        GreenInfoList = greenInfoList;
    }

    public List<RedInfo> getRedInfoList() {
        return RedInfoList;
    }

    public void setRedInfoList(List<RedInfo> redInfoList) {
        RedInfoList = redInfoList;
    }
}
