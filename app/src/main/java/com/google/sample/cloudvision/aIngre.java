package com.google.sample.cloudvision;

import com.google.gson.annotations.SerializedName;

public class aIngre {

    @SerializedName("id")
    private String id;

    @SerializedName("type")
    private String type;

    @SerializedName("name")
    private String name;



    public String getId() {
        return id;
    }
    public void setId(String id){
        this.id =id;
    }

    public String getType() {
        return type;
    }
    public void setType(String type){
        this.type = type;
    }

    public String getName() {
        return name;
    }
    public void setName(String name){
        this.id =name;
    }
}
