package com.example.smartcity_v2.beans;

import java.io.Serializable;
import java.util.List;

public class ItemNewsCategory implements Serializable {

    private int id;
    private String name;
    private int sort;

    public ItemNewsCategory(int id, String name, int sort) {
        this.id = id;
        this.name = name;
        this.sort = sort;
    }

    public ItemNewsCategory() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }
}
