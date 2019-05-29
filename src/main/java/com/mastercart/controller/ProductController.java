package com.mastercart.controller;


import java.util.List;

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

import com.mastercart.model.Category;
import com.mastercart.model.Product;
import com.mastercart.model.Shop;
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
    
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Product[] getAllProducts(){
        List<Product> products = productSevice.getAllProducts();
        return products.toArray(new Product[products.size()]);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Product getProductById(@PathVariable Long id){
        return productSevice.getProductById(id);
    }
    
    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
   	public ResponseEntity<ProductDTO> add(@RequestBody ProductDTO product){
    	Shop shop = shopService.getShopById(Long.parseLong(product.getIdShop()));
    	Product pr = productSevice.addProduct(product);
    	Category cat = categoryService.getCategoryById(Long.parseLong(product.getIdCategory()));
    	pr.setCategory(cat);
    	shop.getProducts().add(pr);
    	shopService.save(shop);
    	return new ResponseEntity<ProductDTO>(product, HttpStatus.OK);
       }
    
    @RequestMapping(value = "/edit", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
   	public ResponseEntity<ProductDTO> edit(@RequestBody ProductDTO product){
    	Product editProduct = productSevice.editProduct(product);
    	Category cat = categoryService.getCategoryById(Long.parseLong(product.getIdCategory()));
    	editProduct.setCategory(cat);
    	productSevice.update(editProduct);
   		return new ResponseEntity<ProductDTO>(product, HttpStatus.OK);
       }


}
