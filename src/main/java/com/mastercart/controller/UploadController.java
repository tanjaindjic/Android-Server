package com.mastercart.controller;

import com.mastercart.model.Shop;
import com.mastercart.service.ShopSevice;
import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;
import org.springframework.util.*;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
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
import com.mastercart.security.TokenUtils;
import com.mastercart.service.UploadSevice;
import com.mastercart.service.UserService;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class UploadController {

	@Autowired
    private UploadSevice uploadSevice;

	@Autowired
	private TokenUtils tokenUtils;
	@Autowired
	private UserService userService;
	@Autowired
	private ShopSevice shopSevice;

	@RequestMapping(value = "upload", method = RequestMethod.POST, consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> uploadImage(@RequestHeader(value="Authorization") String Authorization, @RequestBody String file) throws IOException, Base64DecodingException {
    	String email = tokenUtils.getUsernameFromToken(Authorization);
	    if(email==null || email.isEmpty()) {
	    	System.out.println("Pokusaj neautorizovanog dodavanja slike");	    	
	    	return new ResponseEntity<>(null, HttpStatus.OK);
	    }
	    User user = userService.getUserByEmail(email);	    
	    if(user == null) {
	    	return new ResponseEntity<>(null, HttpStatus.OK);
	    }

		/*FileItem fileItem = new DiskFileItem(user.getEmail(), "image/jpeg",true, user.getEmail(), 100000000, new java.io.File(System.getProperty("java.io.tmpdir")));
	    fileItem.getOutputStream();
		MultipartFile multipartFile = new CommonsMultipartFile(fileItem);*/
	    user.setImageResource( Base64.decode(new String(file).getBytes("UTF-8")));
	    userService.update(user);
    	return new ResponseEntity<String>("done", HttpStatus.OK);
    }

	@RequestMapping(value = "uploadShop", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> uploadShopImage( @RequestBody List<String> file) throws IOException, Base64DecodingException {

		Shop s = shopSevice.getShopById(Long.parseLong(file.get(1)));
		if(s!=null) {
			s.setImageResource(Base64.decode(new String(file.get(0)).getBytes("UTF-8")));
			shopSevice.save(s);
			return new ResponseEntity<String>("done", HttpStatus.OK);
		}else return new ResponseEntity<String>("not done", HttpStatus.BAD_REQUEST);
	}
	
}
