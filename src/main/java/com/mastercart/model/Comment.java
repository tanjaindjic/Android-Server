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
    private double review;

    public Comment() {
    }

    public Comment(Shop forShop, Product forProduct, String user, String comment, Date time, double review) {
        this.forShop = forShop;
        this.forProduct = forProduct;
        this.user = user;
        this.comment = comment;
        this.time = time;
        this.review = review;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Object getForShop() {
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

    public double getReview() {
        return review;
    }

    public void setReview(double review) {
        this.review = review;
    }

    public Product getForProduct() {
        return forProduct;
    }

    public void setForProduct(Product forProduct) {
        this.forProduct = forProduct;
    }
}
