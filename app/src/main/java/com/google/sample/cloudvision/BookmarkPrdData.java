package com.google.sample.cloudvision;

public class BookmarkPrdData {
    private int prdImage;
    private String prdName;
    private int delete;
    private int water;

    public BookmarkPrdData(int prdImage,String prdName,int delete, int water) {
        this.prdImage = prdImage;
        this.prdName = prdName;
        this.delete = delete;
        this.water = water;
    }
    public int getPrdImage()
    {
        return this.prdImage;
    }

    public String getPrdName()
    {
        return this.prdName;
    }

    public int getDelete()
    {
        return this.delete;
    }

    public int getWater() {return  this.water;}
}