package com.mastercart.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Comment {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Shop forShop; //id radnje ili proizvoda
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Product forProduct; //id radnje ili proizvoda
    private String user;
    private String comment;
    private Date time;
    private double shopRating;
    private double productRating;
    private Long orderId;

    public Comment() {
    }

    public Comment(Shop forShop, Product forProduct, String user, String comment, Date time, double shopRating, double productReview, Long orderId) {
        this.forShop = forShop;
        this.forProduct = forProduct;
        this.user = user;
        this.comment = comment;
        this.time = time;
        this.shopRating = shopRating;
        this.productRating = productReview;
        this.orderId = orderId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Shop getForShop() {
        return forShop;
    }

    public void setForShop(Shop forShop) {
        this.forShop = forShop;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public double getShopRating() {
        return shopRating;
    }

    public void setShopRating(double shopRating) {
        this.shopRating = shopRating;
    }

    public Product getForProduct() {
        return forProduct;
    }

    public void setForProduct(Product forProduct) {
        this.forProduct = forProduct;
    }

    public double getProductRating() {
        return productRating;
    }

    public void setProductRating(double productRating) {
        this.productRating = productRating;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
