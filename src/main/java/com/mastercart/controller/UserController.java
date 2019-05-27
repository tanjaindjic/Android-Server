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
import com.mastercart.model.dto.AddUserDTO;
import com.mastercart.model.dto.EditUserDTO;
import com.mastercart.security.TokenUtils;
import com.mastercart.service.UserService;

@RestController
public class UserController {

	@Autowired
	private TokenUtils tokenUtils;
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "user", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> addUser(@RequestBody AddUserDTO userDTO){
    	User tryEmail = userService.getUserByEmail(userDTO.getEmail());
    	if(tryEmail!=null) {
        	return new ResponseEntity<>(null, HttpStatus.OK);    		
    	}
    	System.out.println("dodavanje korisnika");
    	User response = userService.addUser(userDTO);
    	return new ResponseEntity<User>(response, HttpStatus.OK);
    }
	@RequestMapping(value = "user", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> addUser(@RequestHeader(value="Authorization") String Authorization, @RequestBody EditUserDTO userDTO){
		String email = tokenUtils.getUsernameFromToken(Authorization);
	    if(email==null || email.isEmpty() || !email.equals(userDTO.getEmail())) {
	    	System.out.println("Pokusaj neautorizovanog update-ovanja profila");	    	
	    	return new ResponseEntity<>(null, HttpStatus.OK);
	    }
	    User user = userService.getUserByEmail(email);
	    System.out.println("update korisnika");
    	User response = userService.updateUser(userDTO, user);
    	return new ResponseEntity<User>(response, HttpStatus.OK);
    }
}
