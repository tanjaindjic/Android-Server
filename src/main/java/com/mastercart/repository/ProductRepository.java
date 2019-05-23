package com.mastercart.repository;

import com.mastercart.model.Product;
import com.mastercart.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
