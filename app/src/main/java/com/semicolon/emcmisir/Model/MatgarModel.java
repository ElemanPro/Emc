package com.semicolon.emcmisir.Model;

import java.io.Serializable;


public class MatgarModel implements Serializable{
    private String matgar_pk;
    private String client_name ;
    private String client_details;
    private String product_name;
    private String product_price;
    private String product_image;
    private String date;

    public MatgarModel() {
    }

    public MatgarModel(String matgar_pk, String client_name, String client_details, String product_name, String product_price, String product_image, String date) {
        this.matgar_pk = matgar_pk;
        this.client_name = client_name;
        this.client_details = client_details;
        this.product_name = product_name;
        this.product_price = product_price;
        this.product_image = product_image;
        this.date = date;
    }

    public String getMatgar_pk() {
        return matgar_pk;
    }

    public void setMatgar_pk(String matgar_pk) {
        this.matgar_pk = matgar_pk;
    }

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    public String getClient_details() {
        return client_details;
    }

    public void setClient_details(String client_details) {
        this.client_details = client_details;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_price() {
        return product_price;
    }

    public void setProduct_price(String product_price) {
        this.product_price = product_price;
    }

    public String getProduct_image() {
        return product_image;
    }

    public void setProduct_image(String product_image) {
        this.product_image = product_image;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
