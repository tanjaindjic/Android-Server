package com.mastercart.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mastercart.model.Conversation;
import com.mastercart.model.Message;
import com.mastercart.model.Shop;
import com.mastercart.model.User;
import com.mastercart.model.dto.ConversationDTO;
import com.mastercart.repository.ConversationRepository;
import com.mastercart.repository.MessageRepository;

@Service
public class ConversationService {

	@Autowired
	private ConversationRepository conversationRepository;
	@Autowired
	private MessageRepository messageRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private ShopSevice shopService;
	
	public void save(Conversation c) {
		conversationRepository.save(c);
	}
	
	public Conversation add(ConversationDTO conversationDTO) {
		User user = userService.getUserByEmail(conversationDTO.getUserEmail());
    	Shop shop = shopService.getShopById(Long.parseLong(conversationDTO.getShopId()));
    	User seller = userService.getUserByEmail(shop.getSeller().getEmail());
    	Message ms = new Message();
    	ms.setUserSender(user);
    	ms.setShopSender(shop);
    	ms.setMessage(conversationDTO.getMessage());
    	ms.setTime(new Date());
    	messageRepository.save(ms);
		Conversation con = new Conversation();
    	con.setShop(shop);
    	con.getMessages().add(ms);
    	conversationRepository.save(con);
    	ms.setConversation(con);
    	messageRepository.save(ms);
    	user.getConversations().add(con);
    	userService.update(user);
    	con.setInitiator(user);
    	conversationRepository.save(con);
    	con.setReceiver(seller);
    	conversationRepository.save(con);
    	seller.getConversations().add(con);
    	userService.update(seller);
    	return con;
	}
	
	public Conversation findById(String id) {
		return conversationRepository.findById(Long.valueOf(id)).get();
	}
}
