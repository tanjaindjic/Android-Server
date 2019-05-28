package com.mastercart.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mastercart.model.Product;
import com.mastercart.model.dto.ProductDTO;
import com.mastercart.repository.ProductRepository;

@Service
public class ProductSevice {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Product getProductById(Long id){
        return productRepository.findById(id).get();
    }
    
    public Product addProduct(ProductDTO product) {
    	Product newProduct = new Product();
    	newProduct.setName(product.getName());
    	newProduct.setDescription(product.getDescription());
    	newProduct.setDiscount(Integer.parseInt(product.getDiscount()));
    	newProduct.setPrice(Double.parseDouble(product.getPrice()));
    	newProduct.setSize(product.getSize());
    //	newProduct.setOnStock(Integer.getInteger(product.getOnStock()));
    	productRepository.save(newProduct);
    	return newProduct;
    }

    public Product editProduct(ProductDTO product) {
    	Product newProduct = productRepository.findById(Long.parseLong(product.getId())).get();
    	if(product.getName()!="")
    		newProduct.setName(product.getName());
    	if(product.getDescription()!="")
    		newProduct.setDescription(product.getDescription());
    	if(product.getDiscount()!="")
    		newProduct.setDiscount(Integer.parseInt(product.getDiscount()));
    	if(product.getPrice()!="")
    		newProduct.setPrice(Double.parseDouble(product.getPrice()));
    	if(product.getSize()!="")
    		newProduct.setSize(product.getSize());
    //	newProduct.setOnStock(Integer.getInteger(product.getOnStock()));
    	productRepository.save(newProduct);
    	return newProduct;
    }


}
