package com.mastercart.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.mastercart.model.User;
import com.mastercart.model.enums.Role;

public class CustomUserDetailsFactory {

	private CustomUserDetailsFactory() {}
	
	public static CustomUserDetails createCustomUserDetails(User korisnik) {

		return new CustomUserDetails(
				korisnik.getEmail(),
				korisnik.getId(),
				mapToGrantedAuthorities(korisnik.getRole())
				);
	}
	
	private static List<GrantedAuthority> mapToGrantedAuthorities(Role role) {
        
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(role.toString()));
		
		return authorities;
    }
	
}
