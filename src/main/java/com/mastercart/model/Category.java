package com.mastercart.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
@Entity
public class Category {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private ArrayList<Product> categoryItems;

    public Category(int id, String name, ArrayList<Product> categoryItems) {
        this.id = id;
        this.name = name;
        this.categoryItems = categoryItems;
    }

    public Category() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Product> getCategoryItems() {
        return categoryItems;
    }

    public void setCategoryItems(ArrayList<Product> categoryItems) {
        this.categoryItems = categoryItems;
    }
}
