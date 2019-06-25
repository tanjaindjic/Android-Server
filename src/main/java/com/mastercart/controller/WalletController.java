package com.mastercart.controller;

import java.io.IOException;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mastercart.model.User;
import com.mastercart.model.Wallet;
import com.mastercart.model.dto.WalletDTO;
import com.mastercart.service.UserService;
import com.mastercart.service.WalletService;

@RestController
@RequestMapping(value="wallet")
public class WalletController {
	
	@Autowired
	private UserService usSer;
	
	@Autowired 
	private WalletService wallSer;
	
	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
   	public ResponseEntity<WalletDTO> add(@RequestBody WalletDTO walletDTO) throws IOException, URISyntaxException{
    	User us = usSer.getUserByEmail(walletDTO.getUserEmail());
    	Wallet wallet = wallSer.findByUserEmail(us.getEmail());
    	wallet.setBalance(wallet.getBalance()+Double.parseDouble(walletDTO.getBalance()));
    	wallSer.update(wallet);
    	return new ResponseEntity<WalletDTO>(walletDTO, HttpStatus.OK);
       }

}
