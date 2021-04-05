package com.elearning.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.elearning.dao.UserRepository;
import com.elearning.model.User;

import java.util.*;

@Service(value = "userService")
public class MyUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepository<User> ur;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = ur.findByUsername(username);
		if(user == null){
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		
		//return new MyUser(user.getUsername(), user.getPassword(), getAuthority(user),user.getId());
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user));
	}

	private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
		});
		return authorities;
	}   
}