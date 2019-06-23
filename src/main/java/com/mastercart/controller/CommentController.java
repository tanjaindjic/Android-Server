package com.mastercart.controller;

import com.mastercart.model.*;
import com.mastercart.model.dto.ReviewDTO;
import com.mastercart.model.enums.OrderStatus;
import com.mastercart.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mastercart.model.dto.CommentDTO;
import com.mastercart.security.TokenUtils;

@RestController
public class CommentController {
	@Autowired
	private TokenUtils tokenUtils;
	@Autowired
	private UserService userService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private ShopSevice shopSevice;
	@Autowired
	private ProductSevice productSevice;
	@Autowired
	private OrderSevice orderSevice;
	
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
    @RequestMapping(value = "review", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> addReview(@RequestBody ReviewDTO comment){
		Product p = productSevice.getProductById(comment.getProductId());
		if(p==null)
			return new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
		Shop s = shopSevice.getShopById(comment.getShopId());
		if(s == null)
			return new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
		Order o = orderSevice.getOrderById(comment.getOrderId());
		if(o == null)
			return new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
		Comment c = new Comment(s, p, comment.getUser(), comment.getComment(), comment.getDate(), comment.getShopRating(), comment.getProductRating(), comment.getOrderId());
		c = commentService.saveComment(c);
		p.getComments().add(c);
		double newRating = commentService.calculateProductRating(p, c);
		p.setRating(newRating);
		p.setNumberOfRatings(p.getNumberOfRatings()+1);
		productSevice.update(p);
		//izracunati novi rejting
		s.getComments().add(c);
		newRating = commentService.calculateShopRating(s, c);
		s.setRating(newRating);
		s.setNumberOfRatings(s.getNumberOfRatings()+1);
		shopSevice.save(s);
		o.setOrderStatus(OrderStatus.RATED);
		orderSevice.save(o);
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
	

}
