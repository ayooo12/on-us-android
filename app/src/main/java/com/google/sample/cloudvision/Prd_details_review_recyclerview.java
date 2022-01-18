package com.google.sample.cloudvision;

public class Prd_details_review_recyclerview {
    String date;
    String cmp_name;
    String writer;
    String review;
    String num;
    int img_cmp;
    int star;


    public Prd_details_review_recyclerview(String date, String cmp_name, String writer,
                                           String review, String num, int img_cmp, int star){
        this.date=date;
        this.cmp_name=cmp_name;
        this.writer=writer;
        this.review=review;
        this.num=num;
        this.img_cmp=img_cmp;
        this.star=star;
    }

    public String getDate() { return date; }
    public void setDate(String date) {
        this.date = date;
    }
    public String getCmp_name() { return cmp_name; }
    public void setCmp_name(String cmp_name) {
        this.cmp_name = cmp_name;
    }
    public String getWriter() { return writer; }
    public void setWriter(String writer) {
        this.writer = writer;
    }
    public String getReview() { return review; }
    public void setReview(String review) {
        this.review = review;
    }
    public String getNum() { return num; }
    public void setNum(String num) {
        this.num = num;
    }
    public int getImg_cmp() {
        return img_cmp;
    }
    public void setImg_cmp(int img_cmp) {
        this.img_cmp = img_cmp;
    }
    public int getStar() {
        return star;
    }
    public void setStar(int star) {
        this.star = star;
    }
}