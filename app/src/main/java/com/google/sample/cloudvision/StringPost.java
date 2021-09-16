package com.google.sample.cloudvision;

import com.google.gson.annotations.SerializedName;

public class StringPost {

    @SerializedName("names")
    private String names;

    public String getSliceNames() {
        return names;
    }
    public void setSliceNames(String name){
        this.names = names;
    }

}
