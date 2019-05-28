package com.mastercart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mastercart.model.Comment;
import com.mastercart.model.User;
import com.mastercart.model.dto.CommentDTO;
import com.mastercart.security.TokenUtils;
import com.mastercart.service.CommentService;
import com.mastercart.service.UserService;

@RestController
public class CommentController {
	@Autowired
	private TokenUtils tokenUtils;
	@Autowired
	private UserService userService;
	@Autowired
	private CommentService commentService;
	
	@RequestMapping(value = "comment", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Comment> addComment(@RequestHeader(value="Authorization") String Authorization, @RequestBody CommentDTO commentDTO){
		String email = tokenUtils.getUsernameFromToken(Authorization);
	    if(email==null || email.isEmpty()) {    	
	    	return new ResponseEntity<>(null, HttpStatus.OK);
	    }		
		User user = userService.getUserByEmail(email);
    	if(user==null) {
        	return new ResponseEntity<>(null, HttpStatus.OK);    		
    	}
    	Comment response = commentService.addComment(commentDTO, user);
    	if(response==null)
	    	return new ResponseEntity<>(null, HttpStatus.OK);
    	System.out.println("uneo komentar");
    	return new ResponseEntity<Comment>(response, HttpStatus.OK);
    }
	

}
