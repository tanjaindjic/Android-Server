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

  


}
