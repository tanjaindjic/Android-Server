package com.mastercart.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

@Entity
public class Conversation {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JsonBackReference
    Shop reciever;
    @ManyToOne
    @JsonBackReference
    User initiator;
    @OneToMany
    List<Message> messages;

    public Conversation(int id, Shop receiver, User initiator, List<Message> messages) {
        this.reciever = receiver;
        this.initiator = initiator;
        this.messages = messages;
    }

    public Conversation() { }

    public Long getId() {
        return id;
    }

    public Shop getReciever() {
        return reciever;
    }

    public void setReciever(Shop reciever) {
        this.reciever = reciever;
    }

    public User getInitiator() {
        return initiator;
    }

    public void setInitiator(User initiator) {
        this.initiator = initiator;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
