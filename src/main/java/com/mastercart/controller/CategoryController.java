package com.mastercart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mastercart.model.Category;
import com.mastercart.model.Product;
import com.mastercart.service.CategorySevice;

@RestController
@RequestMapping(value = "/category")
public class CategoryController {

    @Autowired
    private CategorySevice categorySevice;
    
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Category addCategory(@RequestParam("name") String name){
    	return categorySevice.addCategory(name);
    }

}
