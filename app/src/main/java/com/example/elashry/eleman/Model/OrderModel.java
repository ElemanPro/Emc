package com.example.elashry.eleman.Model;

/**
 * Created by Delta on 06/08/2017.
 */

public class OrderModel
{
    String order_id;
    String product_id;
    String quantity;
    String client_name;
    String client_phone;
    String order_date;
    String order_address;

    public OrderModel(String order_id, String product_id, String quantity, String client_name, String client_phone, String order_date, String order_address) {
        this.order_id = order_id;
        this.product_id = product_id;
        this.quantity = quantity;
        this.client_name = client_name;
        this.client_phone = client_phone;
        this.order_date = order_date;
        this.order_address = order_address;
    }

    public OrderModel() {
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
