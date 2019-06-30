package com.mastercart.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mastercart.model.Conversation;
import com.mastercart.model.Message;
import com.mastercart.model.User;
import com.mastercart.model.dto.ConversationDTO;
import com.mastercart.model.dto.MessageDTO;
import com.mastercart.model.enums.Role;
import com.mastercart.security.TokenUtils;
import com.mastercart.service.ConversationService;
import com.mastercart.service.MessageService;
import com.mastercart.service.UserService;

@RestController
@RequestMapping(value = "/conversation")
public class ConversationController {
	
	@Autowired
	private TokenUtils tokenUtils;
	@Autowired
	private ConversationService conversationService;
	@Autowired
	private UserService userService;
	@Autowired
	private MessageService messageService;
	
	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ConversationDTO> addConversation(@RequestBody ConversationDTO conversationDTO){
		Conversation con = conversationService.add(conversationDTO);
    	return new ResponseEntity<ConversationDTO>(conversationDTO, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/getConversations", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Conversation>> getConversations(@RequestHeader(value="Authorization") String Authorization){
		String email = tokenUtils.getUsernameFromToken(Authorization);
	    if(email==null || email.isEmpty()) {
	    	System.out.println("Pokusaj neautorizovanog update-ovanja profila");	    	
	    	return new ResponseEntity<>(null, HttpStatus.OK);
	    }
	    User user = userService.getUserByEmail(email);
	    return new ResponseEntity<List<Conversation>>(user.getConversations(), HttpStatus.OK);
	  
    }
	
	
	@RequestMapping(value = "/getMessage/{conversationId}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Message>> getMessages(@PathVariable String conversationId){
		Conversation con = conversationService.findById(conversationId);
    	return new ResponseEntity<List<Message>>(con.getMessages(), HttpStatus.OK);
    }
	
	
	@RequestMapping(value = "/message", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Message> sendMessage(@RequestBody MessageDTO messageDTO){
		Conversation con = conversationService.findById(messageDTO.getConversationId());
		Message m = new Message();
		m.setMessage(messageDTO.getContent());
		m.setTime(new Date());
		User us = userService.getUserByEmail(messageDTO.getInitiator());
		if(us.getRole().equals(Role.KUPAC)) {
			m.setUserSender(us);
			m.setShopSender(con.getShop());
		}else if(us.getRole().equals(Role.PRODAVAC)) {
			m.setShopSender(con.getShop());
		}
		messageService.save(m);
		con.getMessages().add(m);
		conversationService.save(con);
		return new ResponseEntity<Message>(m, HttpStatus.OK);
    }
}
