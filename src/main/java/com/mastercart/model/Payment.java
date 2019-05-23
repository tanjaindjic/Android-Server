package com.mastercart.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Payment {

    @Id
    @GeneratedValue
    private int id;
    private double amount;
    private Date date;

    public Payment(int id, double amount, Date date) {
        this.id = id;
        this.amount = amount;
        this.date = date;
    }

    public Payment() { }

    public int getId() {
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
}
