package com.example.elashry.eleman.Model;

import java.io.Serializable;

/**
 * Created by Lincoln on 18/05/16.
 */
public class MatgarModel implements Serializable{
    private String name;
    private int priceOfProduct;
    private int thumbnail;

    public MatgarModel() {
    }

    public MatgarModel(String name, int priceOfProduct, int thumbnail) {
        this.name = name;
        this.priceOfProduct = priceOfProduct;
        this.thumbnail = thumbnail;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getpriceOfProduct() {
        return priceOfProduct;
    }

    public void setpriceOfProduct(int priceOfProduct) {
        this.priceOfProduct = priceOfProduct;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}
