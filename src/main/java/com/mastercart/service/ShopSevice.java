package com.mastercart.service;


import com.mastercart.model.Shop;
import com.mastercart.model.dto.ShopDTO;
import com.mastercart.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopSevice {

    @Autowired
    private ShopRepository shopRepository;

    public List<Shop> getAllShops() {
        return shopRepository.findAll();
    }

    public Shop getShopById(Long id) {
        return shopRepository.findById(id).get();
    }
    
    public Shop addShop(ShopDTO shop) {
		Shop newShop = new Shop();
		newShop.setName(shop.getName());
		newShop.setEmail(shop.getEmail());
		newShop.setLocation(shop.getLocation());
		newShop.setLat(Double.parseDouble(shop.getLat()));
		newShop.setLng(Double.parseDouble(shop.getLng()));
		newShop.setPhone(shop.getPhone());
		newShop.setImageResource(shop.getImageResource());
		return shopRepository.save(newShop);
	}
}
