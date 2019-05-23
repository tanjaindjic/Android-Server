package com.mastercart.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Comment {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JsonBackReference
    private Shop forShop; //id radnje ili proizvoda
    @ManyToOne
    @JsonBackReference
    private Product forProduct; //id radnje ili proizvoda
    private String user;
    private String store;
    private String comment;
    private Date time;
    @Min(1)
    @Max(5)
    private int review;

    public Comment() {
    }

    public Comment(Shop forShop, Product forProduct, String user, String store, String comment, Date time, int review) {
        this.forShop = forShop;
        this.forProduct = forProduct;
        this.user = user;
        this.store = store;
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

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
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

    public int getReview() {
        return review;
    }

    public void setReview(int review) {
        this.review = review;
    }

    public Product getForProduct() {
        return forProduct;
    }

    public void setForProduct(Product forProduct) {
        this.forProduct = forProduct;
    }
}
