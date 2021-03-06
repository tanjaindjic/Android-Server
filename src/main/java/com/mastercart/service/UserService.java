package com.mastercart.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mastercart.model.CartItem;
import com.mastercart.model.Conversation;
import com.mastercart.model.Order;
import com.mastercart.model.Product;
import com.mastercart.model.User;
import com.mastercart.model.Wallet;
import com.mastercart.model.dto.AddUserDTO;
import com.mastercart.model.dto.EditUserDTO;
import com.mastercart.model.enums.Role;
import com.mastercart.model.enums.StatusCartItem;
import com.mastercart.repository.CartItemRepository;
import com.mastercart.repository.UserRepository;
import com.mastercart.repository.WalletRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private WalletRepository walletRepository;

	@Autowired
	private CartItemRepository cartItemRepository; 
	
	public User getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	public User addUser(AddUserDTO userDTO) {
		User user = new User( userDTO.getEmail(), userDTO.getPassword(), userDTO.getFirstName(), userDTO.getLastName(), "", "", Role.KUPAC, new byte[0], new ArrayList<Product>(), null, new ArrayList<CartItem>(), new ArrayList<Order>(), new ArrayList<Conversation>(), false);
		Wallet wallet = new Wallet();
		wallet.setBalance(0);
		walletRepository.save(wallet);
		user.setWallet(wallet);
		userRepository.save(user);
		wallet.setUser(user);
		walletRepository.save(wallet);
		return userRepository.save(user);
	}

	public User updateUser(EditUserDTO userDTO, User user) {
		user.setAddress(userDTO.getAddress());
		user.setPassword(userDTO.getPassword());
		user.setPhone(userDTO.getPhone());
		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());
		return userRepository.save(user);
	}

	public void update(User user) {
		userRepository.save(user);		
	}


	public void deleteFavourite(User user, Product product) {
		user.getFavorites().remove(product);
		userRepository.save(user);
	}

	public void addToCart(User user, Product product) {
		CartItem cartItem = new CartItem(1, product.getPrice(), StatusCartItem.UBACENO, product);
		cartItemRepository.save(cartItem);
		user.getCartItems().add(cartItem);
		userRepository.save(user);
	}

	public void addToFavs(User user, Product product) {
		if(user.getFavorites().contains(product))
			return;
		user.getFavorites().add(product);
		userRepository.save(user);
	}

	public void deleteCartItem(User user, CartItem cartItem) {
		user.getCartItems().remove(cartItem);
		userRepository.save(user);
	}

	public Boolean findFavsIds(User user, Long id) {
		ArrayList<Long> retVal = new ArrayList<Long>();
		for(Product p : user.getFavorites()) {
			if(p.getId()==id)
				return true;
		}
		return false;
	}
	
	public List<User> getAllForWallet() {
		List<User> users = userRepository.findAll();
		List<User> usersForWallet = new ArrayList<User>();
		for(int i = 0; i < users.size();i++) {
			if(users.get(i).getRole().equals(Role.KUPAC))
				usersForWallet.add(users.get(i));
		}
		return usersForWallet;
	}

}
