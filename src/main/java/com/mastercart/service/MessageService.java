package com.mastercart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mastercart.model.Message;
import com.mastercart.repository.MessageRepository;

@Service
public class MessageService {

	@Autowired
	private MessageRepository messageRepository;
	
	public void save(Message m) {
		messageRepository.save(m);
	}
}
