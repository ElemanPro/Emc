package com.example.elashry.eleman.Model;

import java.io.Serializable;



public class Image_details_Model implements Serializable {
    private String image_details;
    private String image_url;

    public Image_details_Model() {
    }

    public Image_details_Model(String image_details, String image_url) {
        this.image_details = image_details;
        this.image_url = image_url;
    }

    public String getImage_details() {
        return image_details;
    }

    public void setImage_details(String image_details) {
        this.image_details = image_details;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
