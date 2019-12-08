package com.example.graburger;

import java.io.Serializable;

public class FoodItemModel implements Serializable {

    private int image;
    private String description;

    public FoodItemModel() {
        this.image = R.drawable.burger;
        this.description = "Empty";
    }

    public FoodItemModel(int image, String title) {
        this.image = image;
        this.description = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
