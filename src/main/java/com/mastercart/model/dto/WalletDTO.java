package com.mastercart.model.dto;

public class WalletDTO {

	private String balance;
	private String userEmail;
	
	public WalletDTO() {}
	
	public WalletDTO(String balance, String userEmail) {
		super();
		this.balance = balance;
		this.userEmail = userEmail;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	
	  
	  
}
