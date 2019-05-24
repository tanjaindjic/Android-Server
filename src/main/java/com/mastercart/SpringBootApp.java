package com.mastercart;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Initiate spring boot
@SpringBootApplication
public class SpringBootApp {

	public static void main(String[] args) {
		
		// Set runtime class
		SpringApplication.run(SpringBootApp.class, args);
		
		
	}

}
