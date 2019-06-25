package com.mastercart.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Wallet {
    @Id
    @GeneratedValue
    private Long id;
    private double balance;
    @OneToMany
    private List<Payment> history;
    @OneToOne
    @JsonBackReference
    private User user;

    public Wallet( double balance, List<Payment> history, User user) {

        this.balance = balance;
        this.history = history;
        this.user = user;
    }
    
  
    public Wallet() { 
    	
    	this.history = new ArrayList<Payment>();
    	
    }

    public Long getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Payment> getHistory() {
        return history;
    }

    public void setHistory(List<Payment> history) {
        this.history = history;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
