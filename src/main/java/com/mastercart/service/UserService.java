package com.mastercart.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mastercart.model.CartItem;
import com.mastercart.model.Conversation;
import com.mastercart.model.Order;
import com.mastercart.model.Product;
import com.mastercart.model.User;
import com.mastercart.model.dto.AddUserDTO;
import com.mastercart.model.dto.EditUserDTO;
import com.mastercart.model.enums.Role;
import com.mastercart.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository; 
	
	public User getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public User addUser(AddUserDTO userDTO) {
		User user = new User((long) 1, userDTO.getEmail(), userDTO.getPassword(), userDTO.getFirstName(), userDTO.getLastName(), "", "", Role.KUPAC, "", new ArrayList<Product>(), null, new ArrayList<CartItem>(), new ArrayList<Order>(), new ArrayList<Conversation>());
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

}
