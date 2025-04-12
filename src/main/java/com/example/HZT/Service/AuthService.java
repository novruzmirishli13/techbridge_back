package com.example.HZT.Service;

import org.apache.coyote.BadRequestException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.HZT.Entity.User;
import com.example.HZT.Entity.Student;  // Add this import for Student
import com.example.HZT.Exception.NotFoundException;
import com.example.HZT.Model.AuthResponse;
import com.example.HZT.Model.LoginRequestDto;
import com.example.HZT.Model.RegisterRequestDto;
import com.example.HZT.Repository.UserRepository;
import com.example.HZT.Repository.StudentRepository; // Add this import for StudentRepository
import com.example.HZT.Security.JwtUtils;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final StudentRepository studentRepository; // Add this dependency

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtils jwtUtils, StudentRepository studentRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
        this.studentRepository = studentRepository;  // Initialize the studentRepository
    }

    public String registerUser(RegisterRequestDto registerRequest) throws BadRequestException {
        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            throw new BadRequestException("Username is already taken");
        }

        // Register the user
        User user = new User();
        user.setName(registerRequest.getName());
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        userRepository.save(user); // Save the user

        // Create and save a corresponding Student
        Student student = new Student();
        student.setName(registerRequest.getName());
        student.setAge(18); // You can set default values or fetch from the request if available
        student.setUser(user); // Associate student with the registered user
        
        studentRepository.save(student); // Save the student

        return "User and Student registered successfully";
    }

    public AuthResponse loginUser(LoginRequestDto loginRequest) {
        User user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> {
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
