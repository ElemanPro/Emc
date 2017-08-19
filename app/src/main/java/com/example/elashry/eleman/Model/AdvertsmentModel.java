package com.example.elashry.eleman.Model;

/**
 * Created by elashry on 8/16/2017.
 */

public class AdvertsmentModel {

    private String ads_name ;
    private String ads_details;
    private String ads_image;
    private String ads_date;

    public AdvertsmentModel(String ads_name, String ads_details, String ads_image, String ads_date) {
        this.ads_name = ads_name;
        this.ads_details = ads_details;
        this.ads_image = ads_image;
        this.ads_date = ads_date;

    }

    public String getAds_name() {
        return ads_name;
    }

    public String getAds_details() {
        return ads_details;
    }

    public String getAds_image() {
        return ads_image;
    }

    public String getAds_date() {
        return ads_date;
    }
}
