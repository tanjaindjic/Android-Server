package com.mastercart.service;


import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mastercart.model.Product;
import com.mastercart.model.Shop;
import com.mastercart.model.dto.ShopDTO;
import com.mastercart.repository.ShopRepository;

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
    
    public Shop addShop(ShopDTO shop) throws IOException, URISyntaxException {
		Shop newShop = new Shop();
		newShop.setName(shop.getName());
		newShop.setEmail(shop.getEmail());
		newShop.setLocation(shop.getLocation());
		newShop.setLat(Float.parseFloat(shop.getLat()));
		newShop.setLng(Float.parseFloat(shop.getLng()));
		newShop.setPhone(shop.getPhone());
		byte[] data = Files.readAllBytes(Paths.get(this.getClass().getClassLoader().getResource("static/assets/images/charger.jpg").toURI()));
		data = Files.readAllBytes(Paths.get(this.getClass().getClassLoader().getResource("static/assets/images/shop1.png").toURI()));
      
		newShop.setImageResource(data);
		
		return newShop;
	}
    
    public Shop editShop(ShopDTO shop) throws IOException, URISyntaxException {
    	Shop editShop = shopRepository.findById(Long.parseLong(shop.getId())).get();
    	if(shop.getName()!="")
    	   editShop.setName(shop.getName());
    	if(shop.getEmail()!="")
        	editShop.setEmail(shop.getEmail());
    	if(shop.getLocation()!="")
    		editShop.setLocation(shop.getLocation());
    	if(shop.getLat()!="")	
	    	editShop.setLat(Float.parseFloat(shop.getLat()));
    	if(shop.getLng()!="")
        	editShop.setLng(Float.parseFloat(shop.getLng()));
    	if(shop.getPhone()!="")
    		editShop.setPhone(shop.getPhone());
    	
    	byte[] data = Files.readAllBytes(Paths.get(this.getClass().getClassLoader().getResource("static/assets/images/charger.jpg").toURI()));
		data = Files.readAllBytes(Paths.get(this.getClass().getClassLoader().getResource("static/assets/images/shop1.png").toURI()));
        editShop.setImageResource(data);
        	
	    return shopRepository.save(editShop);
	}

	public void updateShop(Shop shop) {
		shopRepository.save(shop);		
	}

	public ArrayList<Shop> getShops(ArrayList<Long> ids) {
		ArrayList<Shop> retVal = new ArrayList<Shop>();
		for(Long i : ids) {
			Shop p0 = shopRepository.getOne(i);
			if(p0!=null)
				retVal.add(p0);
		}
		return retVal;
	}

    public Shop getShopBySellerId(Long id) {
    	return shopRepository.findBySellerId(id);
    }
}
