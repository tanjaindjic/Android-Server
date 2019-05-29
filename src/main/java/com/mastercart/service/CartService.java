package com.mastercart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mastercart.model.CartItem;
import com.mastercart.model.dto.CartItemDTO;
import com.mastercart.repository.CartItemRepository;
import com.mastercart.repository.CategoryRepository;

@Service
public class CartService {
	@Autowired
    private CartItemRepository cartItemRepository;

	public CartItem getProductById(Long id) {
        return cartItemRepository.findById(id).get();
	}

	public CartItem update(CartItemDTO cartItemDTO) {
		CartItem cartItem = cartItemRepository.findById(cartItemDTO.getId()).get();
		cartItem.setQuantity(cartItemDTO.getQuantity());
		cartItem.setTotal(cartItemDTO.getTotal());
		return cartItemRepository.save(cartItem);
	}

}
