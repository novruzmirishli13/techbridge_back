package com.example.HZT.Service;

import org.apache.coyote.BadRequestException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.HZT.Entity.User;
import com.example.HZT.Exception.NotFoundException;
import com.example.HZT.Model.AuthResponse;
import com.example.HZT.Model.LoginRequestDto;
import com.example.HZT.Model.RegisterRequestDto;
import com.example.HZT.Repository.UserRepository;
import com.example.HZT.Security.JwtUtils;

@Service
public class AuthService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtUtils jwtUtils;

	public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtils jwtUtils) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtUtils = jwtUtils;
	}

	public String registerUser(RegisterRequestDto registerRequest) throws BadRequestException {
		if (userRepository.existsByUsername(registerRequest.getUsername())) {
			throw new BadRequestException("Username is already taken");
		}

		User user = new User();
		user.setName(registerRequest.getName());
		user.setUsername(registerRequest.getUsername());
		user.setEmail(registerRequest.getEmail());
		user.setPhoneNumber(registerRequest.getPhoneNumber());
		user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

		userRepository.save(user);

		return "User registered successfully";
	}

	public AuthResponse loginUser(LoginRequestDto loginRequest) {
		User user = userRepository.findByUsername(loginRequest.getUsername())
				.orElseThrow(() ->{
					throw new NotFoundException("User not found");
				});

		if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
			throw new RuntimeException("Invalid username or password");
		}

		String token = jwtUtils.generateJwtToken(org.springframework.security.core.userdetails.User.builder()
				.username(user.getUsername()).password(user.getPassword()).build());
		AuthResponse response = new AuthResponse();
		response.setToken(token);
		return response;
	}
}
