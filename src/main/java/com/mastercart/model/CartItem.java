package com.mastercart.model;


import com.mastercart.model.enums.StatusCartItem;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class CartItem {
    @Id
    @GeneratedValue
    private int id;
    private int quantity;
    private double total;
    private StatusCartItem statusCartItem;
    private Product item;

    public CartItem() {

    }

    public CartItem(int quantity, double total, StatusCartItem statusCartItem, Product item) {
        this.quantity = quantity;
        this.total = total;
        this.statusCartItem = statusCartItem;
        this.item = item;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {   this.id = id;   }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public StatusCartItem getStatusCartItem() {
        return statusCartItem;
    }

    public void setStatusCartItem(StatusCartItem statusCartItem) {
        this.statusCartItem = statusCartItem;
    }

    public Product getItem() {
        return item;
    }

    public void setItem(Product item) {
        this.item = item;
    }
}
