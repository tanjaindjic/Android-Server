package com.mastercart;

import com.google.maps.model.LatLng;
import com.mastercart.model.*;
import com.mastercart.repository.CategoryRepository;
import com.mastercart.repository.ProductRepository;
import com.mastercart.repository.ShopRepository;
import com.mastercart.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;

@Component
public class StartData {

    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CommentService commentService;

    @PostConstruct
    public void init(){
        Shop s1 = new Shop("Mega shop", "", "Test adresa", 35.5, 215.5, "+3815565648", "test@mail.com", true, 3.5, 7, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        shopRepository.save(s1);
        Shop s2 = new Shop("HalloStore", "", "Test adresa", 35.5, 215.5, "+3815565648", "test@mail.com", true, 4.5, 7, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        shopRepository.save(s2);
        Shop s3 = new Shop("Mobile Shop", "", "Test adresa", 35.5, 215.5, "+3815565648", "test@mail.com", true, 2.5, 7, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        shopRepository.save(s3);
        Shop s4 = new Shop("Cells", "", "Test adresa", 35.5, 215.5, "+3815565648", "test@mail.com", true, 5, 7, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        shopRepository.save(s4);

        Category c1 = new Category("Chargers");
        categoryRepository.save(c1);

        Product p1 = new Product("Android Charger", "", 100.0, "Super quality mobile charger!", 12, "20x50cm", 0, true, 4.6, 12, new ArrayList<>(), c1, new ArrayList<>());
        productRepository.save(p1);
        Product p2 = new Product("JBL Speakers", "", 450.0, "Amazing JBL speakers!", 12, "20x50cm", 0, true, 4.6, 12, new ArrayList<>(), c1, new ArrayList<>());
        productRepository.save(p2);
        Product p3 = new Product("Screen Protection", "", 10.0, "Protect your screen now!", 12, "20x50cm", 0, true, 4.6, 12, new ArrayList<>(), c1, new ArrayList<>());
        productRepository.save(p3);
        Product p4 = new Product("Phone Mask", "", 130.0, "Protect your mobile device!", 12, "20x50cm", 0, true, 4.6, 12, new ArrayList<>(), c1, new ArrayList<>());
        productRepository.save(p4);

        Comment com1 = commentService.saveComment(new Comment(s1, null, "John Doe", "Very good shop!", new Date(System.currentTimeMillis()), 4.5));
        Comment com2 = commentService.saveComment(new Comment(s1, null, "Anne Doe", "Good products!", new Date(System.currentTimeMillis()), 4.0));
        Comment com3 = commentService.saveComment(new Comment(s1, null, "Mary Doe", "Meh...", new Date(System.currentTimeMillis()), 2.5));
        Comment com4 = commentService.saveComment(new Comment(null, p1, "John Doe", "Very good product!", new Date(System.currentTimeMillis()), 4.5));
        Comment com5 = commentService.saveComment(new Comment(null, p1, "Anne Doe", "Super!", new Date(System.currentTimeMillis()), 4.5));
        Comment com6 = commentService.saveComment(new Comment(null, p1, "Mary Doe", "Not so good", new Date(System.currentTimeMillis()), 1.5));

        s1.getComments().add(com1);
        s1.getComments().add(com2);
        s1.getComments().add(com3);
        shopRepository.save(s1);
        p1.getComments().add(com4);
        p1.getComments().add(com5);
        p1.getComments().add(com6);
        productRepository.save(p1);


    }


}
