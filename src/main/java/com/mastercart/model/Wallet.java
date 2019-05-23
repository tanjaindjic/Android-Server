package com.mastercart.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.UUID;

@Entity
public class Wallet {
    @Id
    @GeneratedValue
    private int id;
    private double balance;
    private ArrayList<Payment> history;

    public Wallet(int id, double balance, ArrayList<Payment> history) {
        this.id = id;
        this.balance = balance;
        this.history = history;
    }

    public Wallet() {  }

    public int getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public ArrayList<Payment> getHistory() {
        return history;
    }

    public void setHistory(ArrayList<Payment> history) {
        this.history = history;
    }
}
