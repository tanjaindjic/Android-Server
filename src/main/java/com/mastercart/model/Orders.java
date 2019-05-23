package com.mastercart.model;

import java.util.Date;
import com.mastercart.model.enums.OrderStatus;
import com.mastercart.model.enums.OrderType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Orders {
    @Id
    @GeneratedValue
    private int id;
    private Date time;
    private OrderStatus orderStatus;
    private OrderType orderType;
    private double price;

    public Orders() {
    }

    public Orders(int id, Date time, OrderStatus orderStatus, OrderType orderType, double price) {
        this.id = id;
        this.time = time;
        this.orderStatus = orderStatus;
        this.orderType = orderType;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
