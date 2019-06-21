package com.mastercart.repository;

import com.mastercart.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<Shop, Long> {
    Shop findBySellerId(Long id);
}
