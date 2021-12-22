package com.google.sample.cloudvision;

public class search_ing_recyvlerview {
    String ing_name;
    int rightOrwrong;   // 맞아,안맞아 제품 해당 이미지

    public search_ing_recyvlerview(String ing_name,int rightOrwrong){
        this.ing_name=ing_name;
        this.rightOrwrong=rightOrwrong;
    }

    public String getIng_name() {
        return ing_name;
    }

    public void setIng_name(String ing_name) {
        this.ing_name = ing_name;
    }

    public int getRightOrwrong() {
        return rightOrwrong;
    }

    public void setRightOrwrong(int rightOrwrong) {
        this.rightOrwrong = rightOrwrong;
    }
}
