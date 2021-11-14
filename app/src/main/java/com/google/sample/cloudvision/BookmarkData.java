package com.google.sample.cloudvision;

public class BookmarkData {
    private int water;
    private String ingName;
    private int delete;

    public BookmarkData(int water,String ingName,int delete) {
        this.water = water;
        this.ingName = ingName;
        this.delete = delete;
    }
    public int getWater()
    {
        return this.water;
    }

    public String getIngName()
    {
        return this.ingName;
    }

    public int getDelete()
    {
        return this.delete;
    }
}
