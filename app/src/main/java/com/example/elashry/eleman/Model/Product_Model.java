package com.example.elashry.eleman.Model;

import java.io.Serializable;

/**
 * Created by Delta on 01/08/2017.
 */

public class Product_Model implements Serializable {
    String pro_Categ;
    String pro_Name;
    String pro_Price;
    String pro_Image_url;

    public Product_Model() {
    }

    public Product_Model(String pro_Categ, String pro_Name, String pro_Price, String pro_Image_url) {

        this.pro_Categ = pro_Categ;
        this.pro_Name = pro_Name;
        this.pro_Price = pro_Price;
        this.pro_Image_url = pro_Image_url;
    }

    public String getPro_Categ() {
        return pro_Categ;
    }

    public void setPro_Categ(String pro_Categ) {
        this.pro_Categ = pro_Categ;
    }

    public String getPro_Name() {
        return pro_Name;
    }

    public void setPro_Name(String pro_Name) {
        this.pro_Name = pro_Name;
    }

    public String getPro_Price() {
        return pro_Price;
    }

    public void setPro_Price(String pro_Price) {
        this.pro_Price = pro_Price;
    }

    public String getPro_Image_url() {
        return pro_Image_url;
    }

    public void setPro_Image_url(String pro_Image_url) {
        this.pro_Image_url = pro_Image_url;
    }
}
