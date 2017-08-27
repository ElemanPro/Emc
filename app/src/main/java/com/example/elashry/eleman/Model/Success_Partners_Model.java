package com.example.elashry.eleman.Model;

/**
 * Created by Delta on 27/08/2017.
 */

public class Success_Partners_Model
{
    private String name;
    private String image;

    public Success_Partners_Model() {
    }

    public Success_Partners_Model(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
