package com.mastercart.controller;

import com.mastercart.model.Notification;
import com.mastercart.model.Product;
import com.mastercart.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping(value = "/notification")
public class NotificationController {

    @Autowired
    private NotificationRepository notificationRepository;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Product[] getNotification(@PathVariable Long id){
        List<Product> list = new ArrayList<>();
        List<Notification> svi = notificationRepository.findAll();
        for(Notification n :svi)
            if(n.getUserId()==id)
                list.add(n.getProduct());

        for(Notification n : svi)
            if(list.contains(n.getProduct()))
                notificationRepository.deleteById(n.getId());


        return list.toArray(new Product[list.size()]);
    }
}
