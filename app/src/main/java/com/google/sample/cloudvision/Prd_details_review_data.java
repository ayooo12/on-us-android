package com.google.sample.cloudvision;

import com.google.gson.annotations.SerializedName;

public class Prd_details_review_data {

    @SerializedName("title")
    private String title;
    @SerializedName("writer")
    private String writer;
    @SerializedName("date")
    private String date;
    @SerializedName("productRating")
    private String productRating;


    public String getTitle() {
        return title;
    }
    public void setTitle(String title){
        this.title =title;
    }

    public String getWriter() { return writer;}
    public void setWriter(String writer){ this.writer =writer; }

    public String getDate() {
        return date;
    }
    public void setDate(String date){
        this.date =date;
    }

    public String getProductRating() {
        return productRating;
    }
    public void setProductRating(String productRating){
        this.productRating =productRating;
    }
}