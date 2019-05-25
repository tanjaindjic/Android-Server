package com.mastercart.controller;

import com.mastercart.model.Product;
import com.mastercart.model.Shop;
import com.mastercart.service.ShopSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

}
