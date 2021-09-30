package com.google.sample.cloudvision;

import com.google.gson.annotations.SerializedName;

public class frag {

    @SerializedName("id")
    private String id;

    @SerializedName("category")
    private String category;

    @SerializedName("ghsClass")
    private String ghsClass;


    public String getid() {
        return id;
    }
    public void setName(String id){
        this.id =id;
    }

    public String getcategory() {
        return category;
    }
    public void setcategory(String category){
        this.category = category;
    }

    public String getghsClass() {
        return ghsClass;
    }
    public void setghsClass(String ghsClass){
        this.ghsClass = ghsClass;
    }


}
