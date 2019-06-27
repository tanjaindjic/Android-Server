package com.mastercart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mastercart.model.Conversation;
import com.mastercart.model.dto.ConversationDTO;
import com.mastercart.service.ConversationService;

@RestController
@RequestMapping(value = "/conversation")
public class ConversationController {
	
	@Autowired
	private ConversationService conversationService;
	
	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ConversationDTO> addConversation(@RequestBody ConversationDTO conversationDTO){
		Conversation con = conversationService.add(conversationDTO);
    	return new ResponseEntity<ConversationDTO>(conversationDTO, HttpStatus.OK);
    }
}
