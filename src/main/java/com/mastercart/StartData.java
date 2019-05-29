package com.mastercart;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mastercart.model.CartItem;
import com.mastercart.model.Category;
import com.mastercart.model.Comment;
import com.mastercart.model.Conversation;
import com.mastercart.model.Order;
import com.mastercart.model.Payment;
import com.mastercart.model.Product;
import com.mastercart.model.Shop;
import com.mastercart.model.User;
import com.mastercart.model.Wallet;
import com.mastercart.model.enums.OrderStatus;
import com.mastercart.model.enums.OrderType;
import com.mastercart.model.enums.Role;
import com.mastercart.repository.CategoryRepository;
import com.mastercart.repository.OrderRepository;
import com.mastercart.repository.PaymentRepository;
import com.mastercart.repository.ProductRepository;
import com.mastercart.repository.ShopRepository;
import com.mastercart.repository.UserRepository;
import com.mastercart.repository.WalletRepository;
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
    @Autowired
    private PaymentRepository paymentRepository;
    
    

    @PostConstruct
    public void init() throws URISyntaxException, IOException {
        Shop s1 = new Shop("Mega shop", "", "Test adresa", 35.5, 215.5, "+3815565648", "test@mail.com", true, 3.5, 7, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        shopRepository.save(s1);
        Shop s2 = new Shop("HalloStore", "", "Test adresa", 35.5, 215.5, "+3815565648", "test@mail.com", true, 4.5, 7, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        shopRepository.save(s2);
        Shop s3 = new Shop("Mobile Shop", "", "Test adresa", 35.5, 215.5, "+3815565648", "test@mail.com", true, 2.5, 7, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        shopRepository.save(s3);
        Shop s4 = new Shop("Cells", "", "Test adresa", 35.5, 215.5, "+3815565648", "test@mail.com", true, 5, 7, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        shopRepository.save(s4);

        Category c0 = categoryRepository.save(new Category("All"));
        Category c1 = categoryRepository.save(new Category("Chargers"));
        Category c2 = categoryRepository.save(new Category("Earphones"));
        Category c3 = categoryRepository.save(new Category("Phone cases"));
        Category c4 = categoryRepository.save(new Category("Speakers"));
        Category c5 = categoryRepository.save(new Category("Protection"));

        Product p1 = new Product("Android Charger", new byte[0], 100.0, "Super quality mobile charger!", 12, "20x50cm", 0, true, 4.6, 12, new ArrayList<>(), c1, new ArrayList<>());
        productRepository.save(p1);
        Product p2 = new Product("JBL Speakers", new byte[0], 450.0, "Amazing JBL speakers!", 12, "20x50cm", 0, true, 4.6, 12, new ArrayList<>(), c4, new ArrayList<>());
        productRepository.save(p2);
        Product p3 = new Product("Phone Case", new byte[0], 10.0, "Protect your screen now!", 12, "20x50cm", 0, true, 4.6, 12, new ArrayList<>(), c5, new ArrayList<>());
        productRepository.save(p3);
        Product p4 = new Product("Screen Protection", new byte[0], 130.0, "Protect your mobile device!", 12, "20x50cm", 0, true, 4.6, 12, new ArrayList<>(), c5, new ArrayList<>());
        productRepository.save(p4);
        Product p5 = new Product("Earphonoes", new byte[0], 70.0, "High quality sound!", 12, "20x50cm", 0, true, 4.6, 12, new ArrayList<>(), c5, new ArrayList<>());
        productRepository.save(p5);
        Product p6 = new Product("USB cable", new byte[0], 20.0, "Works with any Android phonw!", 12, "20x50cm", 0, true, 4.6, 12, new ArrayList<>(), c5, new ArrayList<>());
        productRepository.save(p6);
        Product p7 = new Product("Phone Mask", new byte[0], 60.0, "Protect your mobile device!", 12, "20x50cm", 0, true, 4.6, 12, new ArrayList<>(), c5, new ArrayList<>());
        productRepository.save(p7);

        byte[] data = Files.readAllBytes(Paths.get(this.getClass().getClassLoader().getResource("static/assets/images/charger.jpg").toURI()));
        p1.setImageResource(data);
        productRepository.save(p1);
        data = Files.readAllBytes(Paths.get(this.getClass().getClassLoader().getResource("static/assets/images/jbl.jpeg").toURI()));
        p2.setImageResource(data);
        productRepository.save(p2);
        data = Files.readAllBytes(Paths.get(this.getClass().getClassLoader().getResource("static/assets/images/mask.jpg").toURI()));
        p3.setImageResource(data);
        productRepository.save(p3);
        data = Files.readAllBytes(Paths.get(this.getClass().getClassLoader().getResource("static/assets/images/screen.jpg").toURI()));
        p4.setImageResource(data);
        productRepository.save(p4);
        data = Files.readAllBytes(Paths.get(this.getClass().getClassLoader().getResource("static/assets/images/earphones.jpg").toURI()));
        p5.setImageResource(data);
        productRepository.save(p5);
        data = Files.readAllBytes(Paths.get(this.getClass().getClassLoader().getResource("static/assets/images/usb.jpg").toURI()));
        p6.setImageResource(data);
        productRepository.save(p6);
        data = Files.readAllBytes(Paths.get(this.getClass().getClassLoader().getResource("static/assets/images/case.jpg").toURI()));
        p7.setImageResource(data);
        productRepository.save(p7);

        Comment com1 = commentService.saveComment(new Comment(s1, null, "John Doe", "Very good shop!", new Date(System.currentTimeMillis()), 4.5));
        Comment com2 = commentService.saveComment(new Comment(s1, null, "Anne Doe", "Good products!", new Date(System.currentTimeMillis()), 4.0));
        Comment com3 = commentService.saveComment(new Comment(s1, null, "Mary Doe", "Meh...", new Date(System.currentTimeMillis()), 2.5));
        Comment com4 = commentService.saveComment(new Comment(null, p1, "John Doe", "Very good product!", new Date(System.currentTimeMillis()), 4.5));
        Comment com5 = commentService.saveComment(new Comment(null, p1, "Anne Doe", "Super!", new Date(System.currentTimeMillis()), 4.5));
        Comment com6 = commentService.saveComment(new Comment(null, p1, "Mary Doe", "Not so good", new Date(System.currentTimeMillis()), 1.5));
        Comment com7 = commentService.saveComment(new Comment(null, p2, "Mary Doe", "Best sound ever!", new Date(System.currentTimeMillis()), 5.0));

        s1.getComments().add(com1);
        s1.getComments().add(com2);
        s1.getComments().add(com3);
        shopRepository.save(s1);
        p1.getComments().add(com4);
        p1.getComments().add(com5);
        p1.getComments().add(com6);
        productRepository.save(p1);
        p2.getComments().add(com7);
        productRepository.save(p2);
        
        Payment py = new Payment();
        py.setAmount(333);
        py.setDate(new Date());
        paymentRepository.save(py);
        Wallet w1 = new Wallet();
        w1.setBalance(123);
        w1.getHistory().add(py);
        py.setWallet(w1);
        walletRepository.save(w1);
        paymentRepository.save(py);

        User admin = new User( "admin@gmail.com", "admin", "Admin", "Adminic", "Kosovska 53", "060/123-123", Role.ADMIN, "", new ArrayList<Product>(), null, new ArrayList<CartItem>(), new ArrayList<Order>(), new ArrayList<Conversation>());
        admin = userRepository.save(admin);
        User seller1 = new User( "seller1@gmail.com", "seller1", "Sale", "Seller", "Puskinova 13", "061/234-234", Role.PRODAVAC, "", new ArrayList<Product>(), null, new ArrayList<CartItem>(), new ArrayList<Order>(), new ArrayList<Conversation>());
        seller1 = userRepository.save(seller1);
        User seller2 = new User("seller2@gmail.com", "seller2", "Proda", "Prodavac", "Strazilovska 13", "062/345-345", Role.PRODAVAC, "", new ArrayList<Product>(), null, new ArrayList<CartItem>(), new ArrayList<Order>(), new ArrayList<Conversation>());
        seller2 = userRepository.save(seller2);
        ArrayList<Product> favMika = new ArrayList<Product>();
        favMika.add(p1);
        favMika.add(p2);
        User buyer1 = new User( "mika@gmail.com", "mika", "Mika", "Mikic", "Mike Antica 26", "063/343-443", Role.KUPAC, "", favMika, w1, new ArrayList<CartItem>(), new ArrayList<Order>(), new ArrayList<Conversation>());
        w1.setUser(buyer1);
        buyer1 = userRepository.save(buyer1);
        walletRepository.save(w1);
        User buyer2 = new User( "pera@gmail.com", "pera", "Pera", "Peric", "Sonje Marinkovic  11", "064/767-696", Role.KUPAC, "", new ArrayList<Product>(), null, new ArrayList<CartItem>(), new ArrayList<Order>(), new ArrayList<Conversation>());
        buyer2 = userRepository.save(buyer2);


        List orders = new ArrayList<>();
        Order o1 = new Order(new Date(System.currentTimeMillis()), OrderStatus.DELIVERED, OrderType.DELIVERY, 100.0, p1, 1, buyer1);
        orderRepository.save(o1);
        orders.add(o1);
        Order o2 = new Order(new Date(System.currentTimeMillis()), OrderStatus.DELIVERED, OrderType.DELIVERY, 50.0, p3, 1, buyer1);
        orderRepository.save(o2);
        orders.add(o2);
        buyer1.setOrders(orders);
        userRepository.save(buyer1);
    }


}
