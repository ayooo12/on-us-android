package com.google.sample.cloudvision;

public class BookmarkPrdData {
    private int prd;
    private String prdName;
    private int delete;

    public BookmarkPrdData(int prd,String prdName,int delete) {
        this.prd = prd;
        this.prdName = prdName;
        this.delete = delete;
    }
    public int getPrd()
    {
        return this.prd;
    }

    public String getPrdName()
    {
        return this.prdName;
    }

    public int getDelete()
    {
        return this.delete;
    }
}