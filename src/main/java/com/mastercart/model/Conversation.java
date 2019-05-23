package com.mastercart.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;
import java.util.UUID;

@Entity
public class Conversation {
    @Id
    @GeneratedValue
    private int id;
    Shop reciever;
    User sender;
    List<Message> messages;

    public Conversation(int id, Shop receiver, User sender, List<Message> messages) {
        this.id = id;
        this.reciever = receiver;
        this.sender = sender;
        this.messages = messages;
    }

    public Conversation() { }

    public int getId() {
        return id;
    }

    public Shop getReciever() {
        return reciever;
    }

    public void setReciever(Shop reciever) {
        this.reciever = reciever;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
