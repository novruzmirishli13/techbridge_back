package com.example.HZT.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.HZT.Security.JwtAuthenticationFilter;
import com.example.HZT.Service.CustomUserDetailsService;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    
    public SecurityConfig(CustomUserDetailsService customUserDetailsService, JwtAuthenticationFilter jwtAuthenticationFilter) {
    	this.customUserDetailsService = customUserDetailsService;
    	this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
     http
     .cors(Customizer.withDefaults())
     .csrf(AbstractHttpConfigurer::disable)
     .authorizeRequests()
     .requestMatchers("/api/count").permitAll()
     .requestMatchers("api/testimonials/*").permitAll()
     .requestMatchers("/api/auth/**").permitAll()
     .requestMatchers("api/game/execute/").permitAll()
     .requestMatchers("api/game/*").permitAll()
     .requestMatchers("api/events").permitAll()
     .requestMatchers("api/images/upload/*").permitAll()
     .requestMatchers("api/images/*").permitAll()
     .requestMatchers("api/progress/update").permitAll()
     .requestMatchers("api/progress/*/*").permitAll()
     .anyRequest().authenticated()
     .and()
     .authenticationProvider(authenticationProvider())
     .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

     return http.build();
}


    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(customUserDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
}
}