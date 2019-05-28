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
    
    public Shop save(Shop s) {
        return shopRepository.save(s);
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
    
    public Shop editShop(ShopDTO shop) {
    	Shop editShop = shopRepository.findById(Long.parseLong(shop.getId())).get();
    	if(shop.getName()!="")
    	   editShop.setName(shop.getName());
    	if(shop.getEmail()!="")
        	editShop.setEmail(shop.getEmail());
    	if(shop.getLocation()!="")
    		editShop.setLocation(shop.getLocation());
    	if(shop.getLat()!="")	
	    	editShop.setLat(Double.parseDouble(shop.getLat()));
    	if(shop.getLng()!="")
        	editShop.setLng(Double.parseDouble(shop.getLng()));
    	if(shop.getPhone()!="")
    		editShop.setPhone(shop.getPhone());
    	if(shop.getImageResource()!="")
        	editShop.setImageResource(shop.getImageResource());
	    return shopRepository.save(editShop);
	}

	public void updateShop(Shop shop) {
		shopRepository.save(shop);		
	}
}
