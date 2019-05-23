package com.mastercart.model;

import java.util.Date;
import com.mastercart.model.enums.OrderStatus;
import com.mastercart.model.enums.OrderType;

import javax.persistence.*;

@Entity
public class Orders {
    @Id
    @GeneratedValue
    private Long id;
    private Date time;
    @Enumerated(value = EnumType.STRING)
    private OrderStatus orderStatus;
    @Enumerated(value = EnumType.STRING)
    private OrderType orderType;
    private double price;

    public Orders() {
    }

    public Orders( Date time, OrderStatus orderStatus, OrderType orderType, double price) {
        this.time = time;
        this.orderStatus = orderStatus;
        this.orderType = orderType;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
