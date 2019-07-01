package com.mastercart.controller;


import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import com.mastercart.model.*;
import com.mastercart.repository.NotificationRepository;
import com.mastercart.service.UserService;
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

import com.mastercart.model.dto.ProductDTO;
import com.mastercart.service.CategorySevice;
import com.mastercart.service.ProductSevice;
import com.mastercart.service.ShopSevice;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    private ProductSevice productSevice;

    @Autowired
    private ShopSevice shopService;
    
    @Autowired
    private CategorySevice categoryService;
    @Autowired
    private UserService userService;
    @Autowired
    private NotificationRepository notificationRepository;
    
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Product[] getAllProducts(){
        List<Product> products = productSevice.getAllProducts();
        return products.toArray(new Product[products.size()]);
    }

    @GetMapping(value = "/seller/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Product[] getSellersProducts(@PathVariable Long id){
        Shop shop = shopService.getShopBySellerId(id);
        List<Product> products = shop.getProducts();
        return products.toArray(new Product[products.size()]);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Product getProductById(@PathVariable Long id){
        return productSevice.getProductById(id);
    }
    
    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
   	public ResponseEntity<ProductDTO> add(@RequestBody ProductDTO product) throws IOException, URISyntaxException{
    	Shop shop = shopService.getShopById(Long.parseLong(product.getIdShop()));
    	Product pr = productSevice.addProduct(product);
    	pr.setShop(shop);
    	Category cat = categoryService.getCategoryById(Long.parseLong(product.getIdCategory()));
    	pr.setCategory(cat);
    	productSevice.update(pr);
    	shop.getProducts().add(pr);
    	shopService.save(shop);
    	product.setId(String.valueOf(pr.getId()));
    	return new ResponseEntity<ProductDTO>(product, HttpStatus.OK);
       }
    
    @RequestMapping(value = "/list", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
   	public ResponseEntity<Product> list(@RequestBody Long id) throws IOException, URISyntaxException{
    	if(id==null) {
        	return new ResponseEntity<>(null, HttpStatus.OK);    		
    	}
    	Product lista = productSevice.getProductById(id);
    	return new ResponseEntity<Product>(lista, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/edit", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
   	public ResponseEntity<ProductDTO> edit(@RequestBody ProductDTO product) throws IOException, URISyntaxException{
    	Product stari = productSevice.getProductById(Long.parseLong(product.getId()));
        double staraCena = stari.getPrice();
        int stariPopust = stari.getDiscount();
        Product editProduct = productSevice.editProduct(product);
    	Category cat = categoryService.getCategoryById(Long.parseLong(product.getIdCategory()));
    	editProduct.setCategory(cat);
    	productSevice.update(editProduct);
    	if(editProduct.getPrice()<staraCena || editProduct.getDiscount()>stariPopust)
            makeNotifications(editProduct);
   		return new ResponseEntity<ProductDTO>(product, HttpStatus.OK);
       }

    private void makeNotifications(Product editProduct) {
        for(User u : userService.getAllForWallet()){
            if(userService.findFavsIds(u, editProduct.getId())) {
                Notification notification = new Notification(u.getId(), editProduct);
                notificationRepository.save(notification);
            }
        }
    }


}
