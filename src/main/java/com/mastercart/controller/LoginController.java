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

import com.mastercart.model.User;
import com.mastercart.model.dto.UserDTO;
import com.mastercart.security.CustomUserDetailsFactory;
import com.mastercart.security.TokenUtils;
import com.mastercart.service.UserService;

@RestController
public class LoginController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private TokenUtils tokenUtils;
	
	@RequestMapping(value = "login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> login(@RequestBody UserDTO forLogin){

	    System.out.println("usao u login");
	    User user = userService.getUserByEmail(forLogin.getEmail());
	    
	    if(user == null) {
	    	return new ResponseEntity<>(null, HttpStatus.OK);
	    }
	    
	    if(!user.getPassword().equals(forLogin.getPassword())) {
		    return new ResponseEntity<>(null, HttpStatus.OK);
	    }
	    
	    String token = tokenUtils.generateToken(CustomUserDetailsFactory.createCustomUserDetails(user));
	    forLogin.setPassword(token);
	  	return new ResponseEntity<UserDTO>(forLogin, HttpStatus.OK);
	}

	@RequestMapping(value = "loggedUser", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> loggedUser(@RequestHeader(value="Authorization") String Authorization){
	    String email = tokenUtils.getUsernameFromToken(Authorization);
	    if(email==null || email.isEmpty()) {
	    	return new ResponseEntity<>(null, HttpStatus.OK);
	    }
	    User user = userService.getUserByEmail(email);
	    if(user==null) {
	    	return new ResponseEntity<>(null, HttpStatus.OK);	    	
	    }
	  	return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	

	@RequestMapping(value = "offline", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> offline(){
	  	return new ResponseEntity<Boolean>(false, HttpStatus.OK);
	}
}
