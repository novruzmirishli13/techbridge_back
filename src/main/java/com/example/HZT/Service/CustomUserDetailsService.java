package com.example.HZT.Service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.example.HZT.Entity.User;
import com.example.HZT.Exception.NotFoundException;
import com.example.HZT.Repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {


	private final UserRepository userRepository;

	public CustomUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() ->{
					throw new NotFoundException("User not found with given id");
				});
		return org.springframework.security.core.userdetails.User.builder()
				.username(user.getUsername())
				.password(user.getPassword())
				.build();
	}
}

