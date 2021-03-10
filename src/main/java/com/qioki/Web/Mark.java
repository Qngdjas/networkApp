package com.qioki.Web;

import java.util.HashMap;

public class Mark {
    String mark;
    String name;

    public Mark(String mark, String name) {
        this.mark = mark;
        this.name = name;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Mark{" +
                "mark='" + mark + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

