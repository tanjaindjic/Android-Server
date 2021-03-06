package com.mastercart.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.List;

import javax.persistence.*;

@Entity
public class Shop {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @Lob
    @Column(columnDefinition="BLOB")
    private byte[] imageResource;
    private String location;
    public float lat;
    public float lng;
    private String phone;
    private String email;
    private boolean active;
    private double rating;
    private int numberOfRatings;
    @OneToMany
    @JsonBackReference
    private List<Product> products;
    @OneToOne
    private User seller;
    @OneToMany(cascade = {CascadeType.ALL, CascadeType.MERGE, CascadeType.PERSIST})
    private List<Comment> comments;

    public Shop() {
    }

    public Shop(String name, byte[] imageResource, String location, float lat, float lng, String phone, String email, boolean active, double rating, int numberOfRatings, List<Product> products, User seller, List<Comment> comments) {

        this.name = name;
        this.imageResource = imageResource;
        this.location = location;
        this.lat = lat;
        this.lng = lng;
        this.phone = phone;
        this.email = email;
        this.active = active;
        this.rating = rating;
        this.numberOfRatings = numberOfRatings;
        this.products = products;
        this.seller = seller;
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", imageResource='" + imageResource + '\'' +
                ", location='" + location + '\'' +
                ", lat=" + lat +
                ", lng=" + lng +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", active=" + active +
                ", rating=" + rating +
                ", numberOfRatings=" + numberOfRatings +
                ", products=" + products +
                ", seller=" + seller +
                ", comments=" + comments +
                '}';
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

    public byte[] getImageResource() {
        return imageResource;
    }

    public void setImageResource(byte[] imageResource) {
        this.imageResource = imageResource;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLng() {
        return lng;
    }

    public void setLng(float lng) {
        this.lng = lng;
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

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
