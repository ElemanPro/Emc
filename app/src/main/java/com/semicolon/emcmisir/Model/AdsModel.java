package com.semicolon.emcmisir.Model;


public class AdsModel {

    private String img;
    private String title;
    private String link;


    public AdsModel(String img, String title,String link) {
        this.img=img;
        this.title=title;
        this.link=link;

    }

    public AdsModel() {

    }

    public String getLink() {
        return link;
    }

    public String getImg() {
        return img;
    }



    public String getTitle() {
        return title;
    }
}