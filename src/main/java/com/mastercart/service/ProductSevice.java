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
    
    public Product addProduct(ProductDTO product) throws IOException, URISyntaxException {
    	Product newProduct = new Product();
    	newProduct.setName(product.getName());
    	newProduct.setDescription(product.getDescription());
    	newProduct.setDiscount(Integer.parseInt(product.getDiscount()));
    	newProduct.setPrice(Double.parseDouble(product.getPrice()));
    	newProduct.setSize(product.getSize());
    	newProduct.setOnStock(Integer.parseInt(product.getOnStock()));
    	newProduct = productRepository.save(newProduct);
    	return newProduct;
    }

    public Product editProduct(ProductDTO product) throws IOException, URISyntaxException {
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
    	if(product.getOnStock()!="")
    		newProduct.setOnStock(Integer.parseInt(product.getOnStock()));
    	
    	byte[] data = Files.readAllBytes(Paths.get(this.getClass().getClassLoader().getResource("static/assets/images/charger.jpg").toURI()));
        newProduct.setImageResource(data);
        
    	productRepository.save(newProduct);
    	return newProduct;
    }

	public void update(Product product) {
		productRepository.save(product);
	}

	public ArrayList<Product> getProducts(ArrayList<Long> ids) {
		ArrayList<Product> retVal = new ArrayList<Product>();
		for(Long i : ids) {
			Product p0 = productRepository.getOne(i);
			if(p0!=null)
				retVal.add(p0);
		}
		return retVal;
	}


}
