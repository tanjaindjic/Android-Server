package com.mastercart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mastercart.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long>{

}
