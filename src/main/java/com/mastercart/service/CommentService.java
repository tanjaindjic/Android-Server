package com.mastercart.service;

import com.mastercart.model.Comment;
import com.mastercart.model.Product;
import com.mastercart.model.Shop;
import com.mastercart.model.User;
import com.mastercart.model.dto.CommentDTO;
import com.mastercart.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class CommentService {
	
	@Autowired
	private ShopSevice shopService;
	@Autowired
	private ProductSevice productSevice;
	@Autowired
	private UserService userService;
    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> getAllShopComments(Long id){
        return commentRepository.findByForShopId(id);
    }

    public List<Comment> getAllProductComments(Long id){
        return commentRepository.findByForProductId(id);
    }

    public Comment saveComment(Comment c){
        return commentRepository.save(c);
    }
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
	    if(commentDTO.getAuthor().isEmpty()) {
	    	commentDTO.setAuthor(user.getFirstName()+" "+user.getLastName());
	    }
		Comment comment = new Comment(shop, product, commentDTO.getAuthor(), commentDTO.getText(), date, commentDTO.getReviev());
		comment = commentRepository.save(comment);
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
