package com.mastercart.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Conversation {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    Shop shop;
    @ManyToOne
    User initiator;
    @ManyToOne
    User receiver;
    @OneToMany
    List<Message> messages;

    public Conversation() {
    	this.messages = new ArrayList<Message>();
    }
   
    public Conversation(Shop shop, User initiator, User receiver, List<Message> messages) {
		super();
		this.shop = shop;
		this.initiator = initiator;
		this.receiver = receiver;
		this.messages = messages;
	}


	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}


	public User getReceiver() {
		return receiver;
	}


	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public Long getId() {
        return id;
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
