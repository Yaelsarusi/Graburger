package com.example.graburger;

public class FoodItemModel {

    private int[] image;
    private String desc;

    public FoodItemModel(){
        this.image = new int[] {};
        this.desc = "Empty";
    }

    public FoodItemModel(int[] image, String title) {
        this.image = image;
        this.desc = title;
    }

    public FoodItemModel(int image, String title) {
        this.image = new int[] {image};
        this.desc = title;
    }

    public int getImage() {
        return image[0];
    }

    public int[] getImages() {
        return image;
    }

    public void setImage(int[] image) { this.image = image; }

    public String getDesc() { return desc; }

    public void setDesc(String desc) { this.desc = desc; }

}
