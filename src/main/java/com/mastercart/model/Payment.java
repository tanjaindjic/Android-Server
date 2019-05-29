package com.mastercart.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Payment {

    @Id
    @GeneratedValue
    private Long id;
    private double amount;
    private Date date;
    @ManyToOne
    @JsonBackReference
    private Wallet wallet;

    public Payment(int id, double amount, Date date, Wallet wallet) {
        this.amount = amount;
        this.date = date;
        this.wallet = wallet;
    }

    public Payment() { }

    public Long getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }
}
