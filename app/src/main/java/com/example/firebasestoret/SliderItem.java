package com.example.firebasestoret;

public class SliderItem {

    public String imageurl,description;

    public SliderItem(){

    }

    public SliderItem(String imageurl, String description) {
        this.imageurl = imageurl;
        this.description = description;
    }

    public String getImageurl() {
        return imageurl;
    }

    public String getDescription() {
        return description;
    }
}
