package com.mastercart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mastercart.model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

}
