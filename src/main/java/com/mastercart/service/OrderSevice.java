package com.mastercart.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mastercart.model.CartItem;
import com.mastercart.model.Order;
import com.mastercart.model.Payment;
import com.mastercart.model.Product;
import com.mastercart.model.User;
import com.mastercart.model.Wallet;
import com.mastercart.model.dto.CartItemDTO;
import com.mastercart.model.enums.OrderStatus;
import com.mastercart.model.enums.OrderType;
import com.mastercart.repository.CartItemRepository;
import com.mastercart.repository.OrderRepository;
import com.mastercart.repository.PaymentRepository;
import com.mastercart.repository.ProductRepository;
import com.mastercart.repository.UserRepository;
import com.mastercart.repository.WalletRepository;

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
	@Autowired
	private WalletRepository walletRepository;
	@Autowired
	private PaymentRepository paymentRepository;
	

	public Order getOrderById(Long id) {
        return orderRepository.findById(id).get();
	}

	public Order addOrder(CartItemDTO cartItemDTO, User user) {
		Wallet wallet = walletRepository.findById(user.getWallet().getId()).get();
		if(wallet.getBalance()-cartItemDTO.getTotal()>0) {
			Calendar cal = Calendar.getInstance();
		    Date date=cal.getTime();
		    Product product = productRepository.findById(cartItemDTO.getProductId()).get();
			Order order = new Order(date, OrderStatus.ORDERED, OrderType.DELIVERY, cartItemDTO.getTotal(), product, cartItemDTO.getQuantity(), user);
			order = orderRepository.save(order);
			//wallet za smanjivanje
			
			wallet.setBalance(wallet.getBalance()-order.getPrice());
			Payment payment = new Payment();
			payment.setAmount(order.getPrice());
			payment.setWallet(wallet);
			payment.setDate(date);
			paymentRepository.save(payment);
			wallet.getHistory().add(payment);
			walletRepository.save(wallet);
			
			CartItem cartItem = cartItemRepository.findById(cartItemDTO.getId()).get();
			user.getCartItems().remove(cartItem);
			cartItemRepository.delete(cartItem);
			user.getOrders().add(order);
			userRepository.save(user);
				
			return order;
			
		}else {
				return null;
		}
	}

	public void save(Order o) {
		orderRepository.save(o);
	}

	public List<Order> getAllOrders(Long id) {
		return orderRepository.findByBuyerId(id);
	}
	
public Order addOrderSubmitAll(List<CartItemDTO> cartItemDTO, User user) {
		
		double sviProizvodi = 0;
		int quantity = 0;
		for(int i=0;i<cartItemDTO.size();i++) {
			sviProizvodi += cartItemDTO.get(i).getTotal();
			quantity += cartItemDTO.get(i).getQuantity();
		}
		Wallet wallet = walletRepository.findById(user.getWallet().getId()).get();
		Calendar cal = Calendar.getInstance();
	    Date date=cal.getTime();
		if(wallet.getBalance() - sviProizvodi>0) {
			for(int i=0;i<cartItemDTO.size();i++) {
			
			    Product product = productRepository.findById(cartItemDTO.get(i).getProductId()).get();
				Order order = new Order(date, OrderStatus.ORDERED, OrderType.DELIVERY, sviProizvodi, product, quantity, user);
				order = orderRepository.save(order);
			
				
				CartItem cartItem = cartItemRepository.findById(cartItemDTO.get(i).getId()).get();
				user.getCartItems().remove(cartItem);
				cartItemRepository.delete(cartItem);
				user.getOrders().add(order);
				userRepository.save(user);
			
				
				wallet.setBalance(wallet.getBalance() - sviProizvodi);
				Payment payment = new Payment();
				payment.setAmount(sviProizvodi);
				payment.setWallet(wallet);
				payment.setDate(date);
				paymentRepository.save(payment);
				wallet.getHistory().add(payment);
				walletRepository.save(wallet);
				
				return order;
			}
				
		}else {
				return null;
		}
		
		return null;
	}
}
