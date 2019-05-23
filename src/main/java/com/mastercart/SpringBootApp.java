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
		NetworkInterface netInter;
		try {
			netInter = NetworkInterface.getNetworkInterfaces().nextElement();
			InetAddress aaa = netInter.getInetAddresses().nextElement();
			String aaaa = aaa.getHostAddress();
			System.out.println("ADRESAA: "+aaaa);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
