package com.mastercart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mastercart.model.Category;
import com.mastercart.repository.CategoryRepository;

@Service
public class CategorySevice {
	@Autowired
    private CategoryRepository categoryRepository;

	public Category addCategory(String name) {
		Category category = new Category();
		category.setName(name);
		return categoryRepository.save(category);
	}
	
	public List<Category> getAll(){
		return categoryRepository.findAll();
	}

	public Category updateCategory(Category category) {
		return categoryRepository.save(category);
	}
	
	
}
