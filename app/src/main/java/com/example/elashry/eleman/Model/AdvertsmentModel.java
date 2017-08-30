package com.example.elashry.eleman.Model;



public class AdvertsmentModel {

    private String ads_name ;
    private String ads_details;
    private String ads_image;
    private String ads_add_date;
    private String ads_expire_date;
    private String ads_type;
    //comment

    public AdvertsmentModel() {
    }

    public AdvertsmentModel(String ads_name, String ads_details, String ads_image, String ads_add_date, String ads_expire_date, String ads_type) {
        this.ads_name = ads_name;
        this.ads_details = ads_details;
        this.ads_image = ads_image;
        this.ads_add_date = ads_add_date;
        this.ads_expire_date = ads_expire_date;
        this.ads_type = ads_type;
    }

    public String getAds_name() {
        return ads_name;
    }

    public void setAds_name(String ads_name) {
        this.ads_name = ads_name;
    }

    public String getAds_details() {
        return ads_details;
    }

    public void setAds_details(String ads_details) {
        this.ads_details = ads_details;
    }

    public String getAds_image() {
        return ads_image;
    }

    public void setAds_image(String ads_image) {
        this.ads_image = ads_image;
    }

    public String getAds_add_date() {
        return ads_add_date;
    }

    public void setAds_add_date(String ads_add_date) {
        this.ads_add_date = ads_add_date;
    }

    public String getAds_expire_date() {
        return ads_expire_date;
    }

    public void setAds_expire_date(String ads_expire_date) {
        this.ads_expire_date = ads_expire_date;
    }

    public String getAds_type() {
        return ads_type;
    }

    public void setAds_type(String ads_type) {
        this.ads_type = ads_type;
    }
}
