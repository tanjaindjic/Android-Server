package com.mastercart.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import com.mastercart.model.Product;
import com.mastercart.service.ProductSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mastercart.model.Shop;
import com.mastercart.model.User;
import com.mastercart.model.dto.ShopDTO;
import com.mastercart.model.enums.Role;
import com.mastercart.service.ShopSevice;
import com.mastercart.service.UserService;

@RestController
@RequestMapping(value = "/shop")
public class ShopController {

    @Autowired
    private ShopSevice shopService;
    
    @Autowired
    private UserService userService;

    @Autowired
    private ProductSevice productSevice;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Shop[] getAllShops(){
        List<Shop> shops = shopService.getAllShops();
        return shops.toArray(new Shop[shops.size()]);
    }

    @GetMapping(value = "/seller/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Shop[] getSellersShops(@PathVariable Long id){
        Shop shop = shopService.getShopBySellerId(id);
        List<Shop> shops = new ArrayList<>();
        shops.add(shop);
        return shops.toArray(new Shop[shops.size()]);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Shop getShopById(@PathVariable Long id){
        return shopService.getShopById(id);
    }
    
    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
   	public ResponseEntity<ShopDTO> add(@RequestBody ShopDTO shop) throws IOException, URISyntaxException{
    	Shop s = shopService.addShop(shop);
       	User us = new User();
       	us.setEmail(shop.getSellerEmail());
       	us.setPassword("111");
       	us.setRole(Role.PRODAVAC);
       	userService.update(us);
       	s.setSeller(us);
       	shopService.save(s);
       	shop.setId(String.valueOf(s.getId()));
       	return new ResponseEntity<ShopDTO>(shop, HttpStatus.OK);
       	
       }
    
    @RequestMapping(value = "/list", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
   	public ResponseEntity<Shop> list(@RequestBody Long id) throws IOException, URISyntaxException{
    	if(id==null) {
        	return new ResponseEntity<>(null, HttpStatus.OK);    		
    	}
    	Shop lista = shopService.getShopById(id);
    	return new ResponseEntity<Shop>(lista, HttpStatus.OK);       	
       }
    
    @RequestMapping(value = "/edit", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
   	public ResponseEntity<ShopDTO> edit(@RequestBody ShopDTO shop) throws IOException, URISyntaxException{
        shopService.editShop(shop);
   		return new ResponseEntity<ShopDTO>(shop, HttpStatus.OK);
       }


}
