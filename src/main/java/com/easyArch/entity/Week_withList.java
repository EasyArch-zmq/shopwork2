package com.easyArch.entity;

import java.io.Serializable;
import java.util.List;

public class Week_withList implements Serializable {
    private Integer week;
    private List<Week_num>list;

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public List<Week_num> getList() {
        return list;
    }

    public void setList(List<Week_num> list) {
        this.list = list;
    }
}
