package com.mastercart;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import com.mastercart.model.enums.OrderStatus;
import com.mastercart.model.enums.OrderType;
import com.mastercart.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mastercart.model.CartItem;
import com.mastercart.model.Category;
import com.mastercart.model.Comment;
import com.mastercart.model.Conversation;
import com.mastercart.model.Order;
import com.mastercart.model.Product;
import com.mastercart.model.Shop;
import com.mastercart.model.User;
import com.mastercart.model.enums.Role;
import com.mastercart.service.CommentService;

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
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WalletRepository walletRepository;
    @Autowired
    private OrderRepository orderRepository;

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
        c1 = categoryRepository.save(c1);
        Category c2 = new Category("Earphones");
        c2 = categoryRepository.save(c2);
        Category c3 = new Category("Phone cases");
        c3 = categoryRepository.save(c3);

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
        
        User admin = new User((long)1, "admin@gmail.com", "admin", "Admin", "Adminic", "Kosovska 53", "060/123-123", Role.ADMIN, "", new ArrayList<Product>(), null, new ArrayList<CartItem>(), new ArrayList<Order>(), new ArrayList<Conversation>());
        admin = userRepository.save(admin);
        User seller1 = new User((long)1, "seller1@gmail.com", "seller1", "Sale", "Seller", "Puskinova 13", "061/234-234", Role.PRODAVAC, "", new ArrayList<Product>(), null, new ArrayList<CartItem>(), new ArrayList<Order>(), new ArrayList<Conversation>());
        seller1 = userRepository.save(seller1);
        User seller2 = new User((long)1, "seller2@gmail.com", "seller2", "Proda", "Prodavac", "Strazilovska 13", "062/345-345", Role.PRODAVAC, "", new ArrayList<Product>(), null, new ArrayList<CartItem>(), new ArrayList<Order>(), new ArrayList<Conversation>());
        seller2 = userRepository.save(seller2);
        User buyer1 = new User((long)1, "mika@gmail.com", "mika", "Mika", "Mikic", "Mike Antica 26", "063/343-443", Role.KUPAC, "", new ArrayList<Product>(), null, new ArrayList<CartItem>(), new ArrayList<Order>(), new ArrayList<Conversation>());
        buyer1 = userRepository.save(buyer1);
        User buyer2 = new User((long)1, "pera@gmail.com", "pera", "Pera", "Peric", "Sonje Marinkovic  11", "064/767-696", Role.KUPAC, "", new ArrayList<Product>(), null, new ArrayList<CartItem>(), new ArrayList<Order>(), new ArrayList<Conversation>());
        buyer2 = userRepository.save(buyer2);

        List orders = new ArrayList<>();
        Order o1 = new Order(new Date(System.currentTimeMillis()), OrderStatus.DELIVERED, OrderType.DELIVERY, 100.00, p1, 1, buyer1);
        orderRepository.save(o1);
        orders.add(o1);
        Order o2 = new Order(new Date(System.currentTimeMillis()), OrderStatus.DELIVERED, OrderType.DELIVERY, 50.00, p3, 1, buyer1);
        orderRepository.save(o2);
        orders.add(o2);
        buyer1.setOrders(orders);
        userRepository.save(buyer1);
    }


}
