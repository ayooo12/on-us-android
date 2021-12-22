package com.google.sample.cloudvision;

import com.google.gson.annotations.SerializedName;

public class Search_product {

    // 성분 검색 객체

    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;


    public String getId() {
        return id;
    }
    public void setId(String id){
        this.id =id;
    }

    public String getName() { return name;}
    public void setName(String name){ this.name =name; }
}
