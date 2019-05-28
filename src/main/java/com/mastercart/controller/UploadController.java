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
import org.springframework.web.multipart.MultipartFile;

import com.mastercart.model.User;
import com.mastercart.model.enums.Role;
import com.mastercart.security.TokenUtils;
import com.mastercart.service.UploadSevice;
import com.mastercart.service.UserService;

@RestController
public class UploadController {

	@Autowired
    private UploadSevice uploadSevice;

	@Autowired
	private TokenUtils tokenUtils;
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> uploadImage(@RequestHeader(value="Authorization") String Authorization, @RequestBody MultipartFile file){
    	String email = tokenUtils.getUsernameFromToken(Authorization);
	    if(email==null || email.isEmpty()) {
	    	System.out.println("Pokusaj neautorizovanog dodavanja slike");	    	
	    	return new ResponseEntity<>(null, HttpStatus.OK);
	    }
	    User user = userService.getUserByEmail(email);	    
	    if(user == null) {
	    	return new ResponseEntity<>(null, HttpStatus.OK);
	    }
	    String path = uploadSevice.uploadImage(file);
	    user.setImageResource(path);
	    System.out.println(path);
	    userService.update(user);
    	return new ResponseEntity<String>("done", HttpStatus.OK);
    }
	
}
