package com.mastercart;

import com.google.maps.model.LatLng;
import com.mastercart.model.*;
import com.mastercart.model.enums.Role;
import com.mastercart.repository.CategoryRepository;
import com.mastercart.repository.ProductRepository;
import com.mastercart.repository.ShopRepository;
import com.mastercart.repository.UserRepository;
import com.mastercart.repository.WalletRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

@Component
public class StartData {

    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private WalletRepository walletRepository;

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
        
        Wallet w_admin = new Wallet(0, null, null);
        w_admin = walletRepository.save(w_admin);
        Wallet w_user1 = new Wallet(0, null, null);
        w_user1 = walletRepository.save(w_user1);
        Wallet w_user2 = new Wallet(0, null, null);
        w_user2 = walletRepository.save(w_user2);
        
        
        User admin = new User("admin@gmail.com", "Admin", "Admminic", "Nova bb", "060/1000-001", Role.ADMIN, "", null, w_admin, null, null, null);
        admin = userRepository.save(admin); 
        User seller1 = new User("seller1@gmail.com", "Sale", "Seler", "Mileticeva 10", "060/2002-002", Role.PRODAVAC, "", null, w_admin, null, null, null);
        seller1 = userRepository.save(seller1); 
        User seller2 = new User("seller2@gmail.com", "Proda", "Prodavac", "Puskinova 43", "060/1000-002", Role.PRODAVAC, "", null, w_admin, null, null, null);
        seller2 = userRepository.save(seller2); 
        User user1 = new User("pera@gmail.com", "Pera", "Peric", "Dunavska 13", "060/1000-004", Role.KUPAC, "", null, w_user1, null, null, null);
        user1 = userRepository.save(user1); 
        User user2 = new User("mika@gmail.com", "Mika", "Mikic", "Radnicka 15", "060/1000-005", Role.KUPAC, "", null, w_user2, null, null, null);
        user2 = userRepository.save(user2); 
        
    }


}
