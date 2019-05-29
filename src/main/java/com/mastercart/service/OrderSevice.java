package com.mastercart.service;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mastercart.model.CartItem;
import com.mastercart.model.Order;
import com.mastercart.model.Product;
import com.mastercart.model.User;
import com.mastercart.model.dto.CartItemDTO;
import com.mastercart.model.enums.OrderStatus;
import com.mastercart.model.enums.OrderType;
import com.mastercart.repository.CartItemRepository;
import com.mastercart.repository.OrderRepository;
import com.mastercart.repository.ProductRepository;
import com.mastercart.repository.UserRepository;

@Service
public class OrderSevice {
	@Autowired
    private OrderRepository orderRepository;
	@Autowired
    private CartItemRepository cartItemRepository;
	@Autowired
    private ProductRepository productRepository;
	@Autowired
    private UserRepository userRepository;

	public Order getProductById(Long id) {
        return orderRepository.findById(id).get();
	}

	public Order addOrder(CartItemDTO cartItemDTO, User user) {
		Calendar cal = Calendar.getInstance();
	    Date date=cal.getTime();
	    Product product = productRepository.findById(cartItemDTO.getProductId()).get();
		Order order = new Order(date, OrderStatus.ORDERED, OrderType.DELIVERY, cartItemDTO.getTotal(), product, cartItemDTO.getQuantity(), user);
		order = orderRepository.save(order);

		CartItem cartItem = cartItemRepository.findById(cartItemDTO.getId()).get();
		user.getCartItems().remove(cartItem);
		cartItemRepository.delete(cartItem);
		user.getOrders().add(order);
		userRepository.save(user);
		return order;
	}
}
