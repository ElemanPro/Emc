package com.semicolon.emcmisir.Model;

import java.io.Serializable;



public class OrderModel implements Serializable{

    String order_id;
    String product_id;
    String matgar_id_fk;
    String quantity;
    String client_name;
    String client_phone;
    String order_date;
    String order_address;

    public OrderModel() {
    }

    public OrderModel(String order_id, String product_id, String matgar_id_fk, String quantity, String client_name, String client_phone, String order_date, String order_address) {
        this.order_id = order_id;
        this.product_id = product_id;
        this.matgar_id_fk = matgar_id_fk;
        this.quantity = quantity;
        this.client_name = client_name;
        this.client_phone = client_phone;
        this.order_date = order_date;
        this.order_address = order_address;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getMatgar_id_fk() {
        return matgar_id_fk;
    }

    public void setMatgar_id_fk(String matgar_id_fk) {
        this.matgar_id_fk = matgar_id_fk;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    public String getClient_phone() {
        return client_phone;
    }

    public void setClient_phone(String client_phone) {
        this.client_phone = client_phone;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public String getOrder_address() {
        return order_address;
    }

    public void setOrder_address(String order_address) {
        this.order_address = order_address;
    }
}
