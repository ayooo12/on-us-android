package com.google.sample.cloudvision;

import com.google.gson.annotations.SerializedName;

public class Func {

    @SerializedName("id")
    private String id;

    @SerializedName("category")
    private String category;

    @SerializedName("type")
    private String type;

    @SerializedName("element")
    private String element;


    public String getId() {
        return id;
    }
    public void setId(String id){
        this.id =id;
    }

    public String getCategory() {
        return category;
    }
    public void setCategory(String category){
        this.category = category;
    }

    public String getType() {
        return type;
    }
    public void setType(String ghsClass){
        this.type = ghsClass;
    }

    public String getElement() {
        return element;
    }
    public void setElement(String element){ this.element = element;
    }


}
