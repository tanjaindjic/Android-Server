package com.mastercart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mastercart.model.Category;
import com.mastercart.model.Product;
import com.mastercart.model.User;
import com.mastercart.security.TokenUtils;
import com.mastercart.service.CategorySevice;
import com.mastercart.service.UserService;

@RestController
public class CategoryController {

    @Autowired
    private CategorySevice categorySevice;

	@Autowired
	private TokenUtils tokenUtils;
    
    @PostMapping(value = "category", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Category> addCategory(@RequestHeader(value="Authorization") String Authorization, @RequestParam("name") String name){
    	String email = tokenUtils.getUsernameFromToken(Authorization);
	    if(email==null || email.isEmpty()) {
	    	System.out.println("Pokusaj neautorizovanog dodavanja kategorije");	    	
	    	return new ResponseEntity<>(null, HttpStatus.OK);
	    }
    	System.out.println("dodavanje kategorije");
    	return new ResponseEntity<Category>(categorySevice.addCategory(name), HttpStatus.OK);
    }

}
