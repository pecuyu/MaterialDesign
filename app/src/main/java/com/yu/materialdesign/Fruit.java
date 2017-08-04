package com.yu.materialdesign;

/**
 * Created by D22436 on 2017/8/4.
 */
public class Fruit {
    private String name;
    private int resId;

    public Fruit(String name, int resId) {
        this.name = name;
        this.resId = resId;
    }

    public String getName() {
        return name;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public void setName(String name) {
        this.name = name;
    }
}
