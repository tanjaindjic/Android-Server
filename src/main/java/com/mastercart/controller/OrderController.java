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

import com.mastercart.model.Category;
import com.mastercart.model.Order;
import com.mastercart.model.User;
import com.mastercart.model.dto.CartItemDTO;
import com.mastercart.model.enums.Role;
import com.mastercart.security.TokenUtils;
import com.mastercart.service.OrderSevice;
import com.mastercart.service.UserService;

@RestController
public class OrderController {
	@Autowired
    private OrderSevice orderSevice;

	@Autowired
	private TokenUtils tokenUtils;
	@Autowired
	private UserService userService;
    
	@RequestMapping(value = "order", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> addOrder(@RequestHeader(value="Authorization") String Authorization, @RequestBody CartItemDTO cartItemDTO){
    	String email = tokenUtils.getUsernameFromToken(Authorization);
	    if(email==null || email.isEmpty()) {
	    	System.out.println("Pokusaj neautorizovanog dodavanja kategorije");	    	
	    	return new ResponseEntity<>(null, HttpStatus.OK);
	    }
	    User user = userService.getUserByEmail(email);	    
	    if(user == null) {
	    	return new ResponseEntity<>(null, HttpStatus.OK);
	    }
    	System.out.println("dodavanje narudzbine");
    	Order order = orderSevice.addOrder(cartItemDTO, user);
    	return new ResponseEntity<Order>(order, HttpStatus.OK);
    }
	

}
