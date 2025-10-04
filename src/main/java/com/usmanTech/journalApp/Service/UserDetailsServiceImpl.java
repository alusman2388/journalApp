package com.usmanTech.journalApp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.usmanTech.journalApp.Entity.Users;
import com.usmanTech.journalApp.Repository.UserRepository;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
 @Autowired
 private UserRepository userRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user=userRepo.findByUserName(username);
		if(user==null) {
	        throw new UsernameNotFoundException("User not found: " + username);
		}
	    return org.springframework.security.core.userdetails.User.builder()
			.username(user.getUserName())
			.password(user.getPassword())
			.roles(user.getRoles().toArray(new String[0]))
			.build();
			
		}
}
