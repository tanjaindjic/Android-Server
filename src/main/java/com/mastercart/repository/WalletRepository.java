package com.mastercart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mastercart.model.Wallet;

public interface WalletRepository extends JpaRepository<Wallet, Long>  {

}
