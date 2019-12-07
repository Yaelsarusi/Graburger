package com.example.graburger;

import java.io.Serializable;

public class FoodItemModel implements Serializable {

    private int image;
    private String desc;

    public FoodItemModel() {
        this.image = R.drawable.burger;
        this.desc = "Empty";
    }


    public FoodItemModel(int image, String title) {
        this.image = image;
        this.desc = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
