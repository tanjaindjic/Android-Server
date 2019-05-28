package com.mastercart.service;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mastercart.model.Comment;
import com.mastercart.model.Product;
import com.mastercart.model.Shop;
import com.mastercart.model.User;
import com.mastercart.model.dto.CommentDTO;
import com.mastercart.repository.CommentRepository;

@Service
public class CommentSevice {

	@Autowired
	private CommentRepository commentRepo;
	@Autowired
	private ShopSevice shopService;
	@Autowired
	private ProductSevice productSevice;
	@Autowired
	private UserService userService;

	public Comment addComment(CommentDTO commentDTO, User user) {
		Shop shop = null;
		Product product = null;
		if(commentDTO.getStoreId()!=null && commentDTO.getStoreId()!=-1) {
			shop = shopService.getShopById(commentDTO.getStoreId());
		}
		if(commentDTO.getProductId()!=null && commentDTO.getProductId()!=-1) {
			product = productSevice.getProductById(commentDTO.getProductId());
		}
		Calendar cal = Calendar.getInstance();
	    Date date=cal.getTime();
		Comment comment = new Comment(shop, product, commentDTO.getAuthor(), commentDTO.getText(), date, commentDTO.getReviev());
		comment = commentRepo.save(comment);
		if(shop!=null) {
			shop.getComments().add(comment);
			shopService.updateShop(shop);
		}
		if(product!=null) {
			product.getComments().add(comment);
			productSevice.update(product);
		}
		return comment;
	}
	
	
}
