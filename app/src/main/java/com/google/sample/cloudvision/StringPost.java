package com.google.sample.cloudvision;

import com.google.gson.annotations.SerializedName;

public class StringPost {

    @SerializedName("componensts")
    private String componensts;

    public String getName() {
        return componensts;
    }
    public void setName(String name){
        this.componensts = componensts;
    }

}
