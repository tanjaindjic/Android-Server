package com.mastercart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mastercart.model.Category;
import com.mastercart.model.Shop;
import com.mastercart.model.User;
import com.mastercart.model.enums.Role;
import com.mastercart.security.TokenUtils;
import com.mastercart.service.CategorySevice;
import com.mastercart.service.UserService;

@RestController
public class CategoryController {

    @Autowired
    private CategorySevice categorySevice;

	@Autowired
	private TokenUtils tokenUtils;
	@Autowired
	private UserService userService;
    
	@RequestMapping(value = "category", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Category> addCategory(@RequestHeader(value="Authorization") String Authorization, @RequestBody String name){
    	String email = tokenUtils.getUsernameFromToken(Authorization);
	    if(email==null || email.isEmpty()) {
	    	System.out.println("Pokusaj neautorizovanog dodavanja kategorije");	    	
	    	return new ResponseEntity<>(null, HttpStatus.OK);
	    }
	    User user = userService.getUserByEmail(email);	    
	    if(user == null || !user.getRole().equals(Role.ADMIN)) {
	    	return new ResponseEntity<>(null, HttpStatus.OK);
	    }
    	System.out.println("dodavanje kategorije");
    	return new ResponseEntity<Category>(categorySevice.addCategory(name), HttpStatus.OK);
    }
	
	@RequestMapping(value = "category", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Category>> getAllCategories(){
    	return new ResponseEntity<List<Category>>(categorySevice.getAll(), HttpStatus.OK);
		
    }
	
	@RequestMapping(value = "category", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Category> updateCategory(@RequestHeader(value="Authorization") String Authorization, @RequestBody Category category){
    	String email = tokenUtils.getUsernameFromToken(Authorization);
	    if(email==null || email.isEmpty()) {
	    	System.out.println("Pokusaj neautorizovanog dodavanja kategorije");	    	
	    	return new ResponseEntity<>(null, HttpStatus.OK);
	    }
	    User user = userService.getUserByEmail(email);	    
	    if(user == null || !user.getRole().equals(Role.ADMIN)) {
	    	return new ResponseEntity<>(null, HttpStatus.OK);
	    }
    	System.out.println("izmena kategorije");
    	return new ResponseEntity<Category>(categorySevice.updateCategory(category), HttpStatus.OK);
    }

}
