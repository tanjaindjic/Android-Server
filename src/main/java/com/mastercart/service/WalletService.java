package com.mastercart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mastercart.model.Wallet;
import com.mastercart.model.dto.WalletDTO;
import com.mastercart.repository.WalletRepository;

@Service
public class WalletService {

	@Autowired
	WalletRepository wallRep;
	
   	public Wallet add(WalletDTO walletDTO){

    	Wallet w = new Wallet();
    	w.setBalance(Double.parseDouble(walletDTO.getBalance()));
    	wallRep.save(w);
    	return w;
   	}
   	
   	
   	public void update(Wallet wallet) {
		wallRep.save(wallet);		
	}
}
