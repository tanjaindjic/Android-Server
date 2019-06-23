package com.mastercart.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Lob
    @Column(columnDefinition="BLOB")
    private byte[] imageResource; //mozda path u storage?
    private double price;
    private String description;
    private int onStock;
    private String size;
    private int discount;
    private boolean active;
    private double rating;
    private int numberOfRatings;
    @OneToMany(cascade = {CascadeType.ALL, CascadeType.MERGE, CascadeType.PERSIST})
    private List<Comment> comments;
    @ManyToOne
    private Category category;
    @OneToMany
    @JsonBackReference
    private List<Order> orders;
    @ManyToOne
    private Shop shop;

    public Product(String name, byte[] imageResource, double price, String description, int onStock, String size, int discount, boolean active, double rating, int numberOfRatings, List<Comment> comments, Category category, List<Order> orders, Shop shop) {
        this.name = name;
        this.imageResource = imageResource;
        this.price = price;
        this.description = description;
        this.onStock = onStock;
        this.size = size;
        this.discount = discount;
        this.active = active;
        this.rating = rating;
        this.numberOfRatings = numberOfRatings;
        this.comments = comments;
        this.category = category;
        this.orders = orders;
        this.shop = shop;
    }

    public Product() {  }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", imageResource='" + imageResource + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", onStock=" + onStock +
                ", size='" + size + '\'' +
                ", discount=" + discount +
                ", active=" + active +
                ", rating=" + rating +
                ", numberOfRatings=" + numberOfRatings +
                ", comments=" + comments +
                ", category=" + category +
                ", orders=" + orders +
                '}';
    }

    public Long getId() {
        return id;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getOnStock() {
        return onStock;
    }

    public void setOnStock(int onStock) {
        this.onStock = onStock;
    }

    public String getSize() {  return size;  }

    public void setSize(String size) {
        this.size = size;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
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

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }
}
