package com.example.elashry.eleman.Model;

/**
 * Created by elashry on 7/31/2017.
 */

public class DataModel {

    private String maintenance_id_pk;
    private String client_name;
    private String client_phone;
    private String client_location;
    private String device_type;
    private String warranty_state;
    private String device_brand;
    private String damage_type;
    private String 	order_date;

    public DataModel(String client_name, String client_phone, String client_location, String device_type, String warranty_state, String device_brand, String damage_type, String order_date) {
        this.client_name = client_name;
        this.client_phone = client_phone;
        this.client_location = client_location;
        this.device_type = device_type;
        this.warranty_state = warranty_state;
        this.device_brand = device_brand;
        this.damage_type = damage_type;
        this.order_date = order_date;
    }

    public void setMaintenance_id_pk(String maintenance_id_pk) {
        this.maintenance_id_pk = maintenance_id_pk;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    public void setClient_phone(String client_phone) {
        this.client_phone = client_phone;
    }

    public void setClient_location(String client_location) {
        this.client_location = client_location;
    }

    public void setDevice_type(String device_type) {
        this.device_type = device_type;
    }

    public void setWarranty_state(String warranty_state) {
        this.warranty_state = warranty_state;
    }

    public void setDevice_brand(String device_brand) {
        this.device_brand = device_brand;
    }

    public void setDamage_type(String damage_type) {
        this.damage_type = damage_type;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public String getClient_name() {
        return client_name;
    }

    public String getClient_phone() {
        return client_phone;
    }

    public String getClient_location() {
        return client_location;
    }

    public String getDevice_type() {
        return device_type;
    }

    public String getWarranty_state() {
        return warranty_state;
    }

    public String getDevice_brand() {
        return device_brand;
    }

    public String getDamage_type() {
        return damage_type;
    }

    public String getOrder_date() {
        return order_date;
    }
}
