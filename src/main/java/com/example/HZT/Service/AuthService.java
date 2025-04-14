package com.example.HZT.Service;

import org.apache.coyote.BadRequestException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.HZT.Entity.User;
import com.example.HZT.Entity.Student;
import com.example.HZT.Enum.UserType;
import com.example.HZT.Exception.NotFoundException;
import com.example.HZT.Model.AuthResponse;
import com.example.HZT.Model.LoginRequestDto;
import com.example.HZT.Model.RegisterRequestDto;
import com.example.HZT.Repository.UserRepository;
import com.example.HZT.Repository.StudentRepository;
import com.example.HZT.Security.JwtUtils;

import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final StudentRepository studentRepository;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtils jwtUtils, StudentRepository studentRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
        this.studentRepository = studentRepository;
    }

    public String registerUser(RegisterRequestDto registerRequest) throws BadRequestException {
        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            throw new BadRequestException("Username is already taken");
        }

        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new BadRequestException("Email is already in use");
        }

        if (registerRequest.getUserType() == null ||
            !(registerRequest.getUserType() == UserType.STUDENT || registerRequest.getUserType() == UserType.PARENT)) {
            throw new BadRequestException("Invalid user type");
        }

        User user = new User();
        user.setName(registerRequest.getName());
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setUserType(registerRequest.getUserType());

        userRepository.save(user);

        if (user.getUserType() == UserType.STUDENT) {
            Student student = new Student();
            student.setName(registerRequest.getName());
            student.setAge(18);
            student.setUser(user);
            studentRepository.save(student);
            return "User registered as Student successfully";
        }

        return "User registered as Parent successfully";
    }

    public AuthResponse loginUser(LoginRequestDto loginRequest) {
        User user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new NotFoundException("User not found"));

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }

        UserBuilder builder = org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword());

        UserDetails userDetails = builder.build();
        String token = jwtUtils.generateJwtToken(userDetails);

        AuthResponse response = new AuthResponse();
        response.setToken(token);
        return response;
    }
}
