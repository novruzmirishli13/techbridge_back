package com.example.HZT.Service;

import org.apache.coyote.BadRequestException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.HZT.Entity.User;
import com.example.HZT.Entity.Student;  // Import for Student
import com.example.HZT.Exception.NotFoundException;
import com.example.HZT.Model.AuthResponse;
import com.example.HZT.Model.LoginRequestDto;
import com.example.HZT.Model.RegisterRequestDto;
import com.example.HZT.Repository.UserRepository;
import com.example.HZT.Repository.StudentRepository; // Import for StudentRepository
import com.example.HZT.Security.JwtUtils;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final StudentRepository studentRepository; // Declare the dependency for StudentRepository

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtils jwtUtils, StudentRepository studentRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
        this.studentRepository = studentRepository;  // Initialize the studentRepository
    }

    public String registerUser(RegisterRequestDto registerRequest) throws BadRequestException {
        // Check if username already exists
        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            throw new BadRequestException("Username is already taken");
        }

        // Register the User
        User user = new User();
        user.setName(registerRequest.getName());
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        userRepository.save(user); // Save the User entity

        // Create and save the corresponding Student
        Student student = new Student();
        student.setName(registerRequest.getName());
        student.setAge(18); // You can set default age or fetch from request if available
        student.setUser(user); // Associate the Student with the User
        
        studentRepository.save(student); // Save the Student entity

        return "User and Student registered successfully";
    }

    public AuthResponse loginUser(LoginRequestDto loginRequest) {
        User user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> {
                    throw new NotFoundException("User not found");
                });

        // Check if password matches
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }

        // Generate JWT token for authentication
        String token = jwtUtils.generateJwtToken(org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername()).password(user.getPassword()).build());
        
        AuthResponse response = new AuthResponse();
        response.setToken(token);  // Set token in response
        return response;
    }
}