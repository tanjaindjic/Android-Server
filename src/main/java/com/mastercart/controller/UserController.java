package com.mastercart.controller;

import java.util.ArrayList;

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

import com.mastercart.model.CartItem;
import com.mastercart.model.Product;
import com.mastercart.model.User;
import com.mastercart.model.dto.AddUserDTO;
import com.mastercart.model.dto.CartItemDTO;
import com.mastercart.model.dto.EditUserDTO;
import com.mastercart.security.TokenUtils;
import com.mastercart.service.CartService;
import com.mastercart.service.ProductSevice;
import com.mastercart.service.UserService;

@RestController
public class UserController {

	@Autowired
	private TokenUtils tokenUtils;
	@Autowired
	private UserService userService;
	@Autowired
	private ProductSevice productSevice;
	@Autowired
	private CartService cartService;
	
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
	
	@RequestMapping(value = "user/favourite/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteFavourite(@RequestHeader(value="Authorization") String Authorization, @PathVariable Long id){
		String email = tokenUtils.getUsernameFromToken(Authorization);
	    if(email==null || email.isEmpty()) {
	    	System.out.println("Pokusaj neautorizovanog update-ovanja profila");	    	
	    	return new ResponseEntity<>(null, HttpStatus.OK);
	    }
	    User user = userService.getUserByEmail(email);
    	System.out.println("brisanje iz omiljenog");
    	Product product = productSevice.getProductById(id);
    	userService.deleteFavourite(user, product);
    	return new ResponseEntity<String>("done", HttpStatus.OK);
    }
	
	@RequestMapping(value = "user/cart", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CartItem> updateCartItem(@RequestHeader(value="Authorization") String Authorization, @RequestBody CartItemDTO cartItemDTO){
		String email = tokenUtils.getUsernameFromToken(Authorization);
	    if(email==null || email.isEmpty()) {
	    	System.out.println("Pokusaj neautorizovane izmene cart item-a");	    	
	    	return new ResponseEntity<>(null, HttpStatus.OK);
	    }
	    User user = userService.getUserByEmail(email);
    	System.out.println("izmena cartItem");
    	CartItem cartItem = cartService.update(cartItemDTO);
    	return new ResponseEntity<CartItem>(cartItem, HttpStatus.OK);
    }
	
	@RequestMapping(value = "user/cart/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteCartItem(@RequestHeader(value="Authorization") String Authorization, @PathVariable Long id){
		String email = tokenUtils.getUsernameFromToken(Authorization);
	    if(email==null || email.isEmpty()) {
	    	System.out.println("Pokusaj neautorizovanog update-ovanja profila");	    	
	    	return new ResponseEntity<>(null, HttpStatus.OK);
	    }
	    User user = userService.getUserByEmail(email);
    	System.out.println("brisanje iz korpe");
    	CartItem cartItem = cartService.getProductById(id);
    	userService.deleteCartItem(user, cartItem);
    	return new ResponseEntity<String>("done", HttpStatus.OK);
    }
	
	@RequestMapping(value = "user/addToCart/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addToCart(@RequestHeader(value="Authorization") String Authorization, @PathVariable Long id){
		String email = tokenUtils.getUsernameFromToken(Authorization);
	    if(email==null || email.isEmpty()) {	    	
	    	return new ResponseEntity<>(null, HttpStatus.OK);
	    }
	    User user = userService.getUserByEmail(email);
    	System.out.println("brisanje iz omiljenog");
    	Product product = productSevice.getProductById(id);
    	userService.addToCart(user, product);
    	return new ResponseEntity<String>("done", HttpStatus.OK);
    }
	
	@RequestMapping(value = "user/addToFavs/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addToFavs(@RequestHeader(value="Authorization") String Authorization, @PathVariable Long id){
		String email = tokenUtils.getUsernameFromToken(Authorization);
	    if(email==null || email.isEmpty()) {  	
	    	return new ResponseEntity<>(null, HttpStatus.OK);
	    }
	    User user = userService.getUserByEmail(email);
    	System.out.println("brisanje iz omiljenog");
    	Product product = productSevice.getProductById(id);
    	userService.addToFavs(user, product);
    	return new ResponseEntity<String>("done", HttpStatus.OK);
    }
	

	@RequestMapping(value = "user/favorite/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> getFavorites(@RequestHeader(value="Authorization") String Authorization, @PathVariable Long id){
		String email = tokenUtils.getUsernameFromToken(Authorization);
	    if(email==null || email.isEmpty()) {  	
	    	return new ResponseEntity<>(false, HttpStatus.OK);
	    }
	    User user = userService.getUserByEmail(email);
    	System.out.println("pretraga favorite proizvoda");
    	Boolean retVal = false;
    	retVal = userService.findFavsIds(user, id);
    	return new ResponseEntity<Boolean>(retVal, HttpStatus.OK);
    }
}
