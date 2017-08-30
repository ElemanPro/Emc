package com.example.elashry.eleman.Model;

import java.io.Serializable;



public class Product_Model implements Serializable {
    String product_id_fk;
    String product_Category_fk;
    String product_title;
    String product_price;
    String product_Image_url;

    public Product_Model() {
    }

    public Product_Model(String product_id_fk, String product_Category_fk, String product_title, String product_price, String product_Image_url) {
        this.product_id_fk = product_id_fk;
        this.product_Category_fk = product_Category_fk;
        this.product_title = product_title;
        this.product_price = product_price;
        this.product_Image_url = product_Image_url;
    }

    public String getProduct_id_fk() {
        return product_id_fk;
    }

    public void setProduct_id_fk(String product_id_fk) {
        this.product_id_fk = product_id_fk;
    }

    public String getProduct_Category_fk() {
        return product_Category_fk;
    }

    public void setProduct_Category_fk(String product_Category_fk) {
        this.product_Category_fk = product_Category_fk;
    }

    public String getProduct_title() {
        return product_title;
    }

    public void setProduct_title(String product_title) {
        this.product_title = product_title;
    }

    public String getProduct_price() {
        return product_price;
    }

    public void setProduct_price(String product_price) {
        this.product_price = product_price;
    }

    public String getProduct_Image_url() {
        return product_Image_url;
    }

    public void setProduct_Image_url(String product_Image_url) {
        this.product_Image_url = product_Image_url;
    }
}
