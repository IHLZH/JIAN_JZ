package com.example.jian_jz.Entity;

import java.io.Serializable;

public class Bill extends BHitem implements Serializable {
    private Integer id;
    private Integer userId;
    private Double cost; //金额
    private String time; //创建时间
    private String sortName; //分类名称
    private Integer sortImg; //分类图标
    private boolean income; //收入支出

    public Bill() {};

    public Bill(Integer id, Integer userId, Double cost, String time, String sortName, Integer sortImg, boolean income) {
        this.id = id;
        this.userId = userId;
        this.cost = cost;
        this.time = time;
        this.sortName = sortName;
        this.sortImg = sortImg;
        this.income = income;
    }

    public Bill(Double cost, String time, String sortName, Integer sortImg, boolean income) {
        this.cost = cost;
        this.time = time;
        this.sortName = sortName;
        this.sortImg = sortImg;
        this.income = income;
    }

    public Bill(int id, Double cost, String time, String sortName, Integer sortImg, boolean income) {
        this.id = id;
        this.cost = cost;
        this.time = time;
        this.sortName = sortName;
        this.sortImg = sortImg;
        this.income = income;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public Integer getSortImg() {
        return sortImg;
    }

    public void setSortImg(Integer sortImg) {
        this.sortImg = sortImg;
    }

    public boolean isIncome() {
        return income;
    }

    public void setIncome(boolean income) {
        this.income = income;
    }
}
