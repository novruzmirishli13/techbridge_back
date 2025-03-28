package com.example.HZT.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
         .cors(AbstractHttpConfigurer::disable)
         .csrf(AbstractHttpConfigurer::disable)
         .authorizeRequests()
         .requestMatchers("/api/auth/**").permitAll()
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