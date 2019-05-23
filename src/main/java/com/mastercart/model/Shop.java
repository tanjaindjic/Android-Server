package com.mastercart.model;

import com.google.maps.model.LatLng;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Shop {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String imageResource;
    private String location;
    private LatLng latlon; //za GoogleMaps
    private String phone;
    private String email;
    private boolean active;
    private double rating;
    private int numberOfRatings;
    @OneToMany
    private List<Product> products;
    @OneToMany
    private List<User> seller;
    @OneToMany
    private List<Comment> comments;

    public Shop() {
    }

    public Shop(String name, String imageResource, String location, LatLng latlon, String phone, String email, boolean active, double rating, int numberOfRatings, List<Product> products, List<User> seller, List<Comment> comments) {
        this.name = name;
        this.imageResource = imageResource;
        this.location = location;
        this.latlon = latlon;
        this.phone = phone;
        this.email = email;
        this.active = active;
        this.rating = rating;
        this.numberOfRatings = numberOfRatings;
        this.products = products;
        this.seller = seller;
        this.comments = comments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageResource() {
        return imageResource;
    }

    public void setImageResource(String imageResource) {
        this.imageResource = imageResource;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LatLng getLatlon() {
        return latlon;
    }

    public void setLatlon(LatLng latlon) {
        this.latlon = latlon;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getNumberOfRatings() {
        return numberOfRatings;
    }

    public void setNumberOfRatings(int numberOfRatings) {
        this.numberOfRatings = numberOfRatings;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<User> getSeller() {
        return seller;
    }

    public void setSeller(List<User> seller) {
        this.seller = seller;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
