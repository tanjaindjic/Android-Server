package com.mastercart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mastercart.model.Shop;
import com.mastercart.model.dto.ShopDTO;
import com.mastercart.service.ShopSevice;

@RestController
@RequestMapping(value = "/shop")
public class ShopController {

    @Autowired
    private ShopSevice shopSevice;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Shop[] getAllShops(){
        List<Shop> shops = shopSevice.getAllShops();
        return shops.toArray(new Shop[shops.size()]);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Shop getShopById(@PathVariable Long id){
        return shopSevice.getShopById(id);
    }
    
    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
   	public Shop add(@RequestBody ShopDTO shop){
       	return shopSevice.addShop(shop);
       	
       }


}
