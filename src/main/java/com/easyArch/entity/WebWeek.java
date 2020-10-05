package com.easyArch.entity;

import java.util.List;

public class WebWeek {
    private String week_str;
    private List<IntWeekNum>list;

    public String getWeek_str() {
        return week_str;
    }

    public void setWeek_str(String week_str) {
        this.week_str = week_str;
    }

    public List<IntWeekNum> getList() {
        return list;
    }

    public void setList(List<IntWeekNum> list) {
        this.list = list;
    }
}
