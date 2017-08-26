package com.example.elashry.eleman.Model;


public class AdsModel {

    private String img;
    private String title;


    public AdsModel(String img, String title) {
        this.img=img;
        this.title=title;
    }

    public AdsModel() {

    }

    public String getImg() {
        return img;
    }



    public String getTitle() {
        return title;
    }
}