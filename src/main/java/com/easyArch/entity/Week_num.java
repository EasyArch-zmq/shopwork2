package com.easyArch.entity;

import java.io.Serializable;

public class Week_num implements Serializable {
    private String week_str;
    private Integer num;

    public String getWeek_str() {
        return week_str;
    }

    public void setWeek_str(String week_str) {
        this.week_str = week_str;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
