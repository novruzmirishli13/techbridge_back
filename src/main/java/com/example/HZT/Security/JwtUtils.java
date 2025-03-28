package com.example.HZT.Security;

import org.springframework.stereotype.Component;
    
@Component
public class JwtUtils {

	 private String jwtSecret = "ASDFGHJKUYTRESDDFVBGHJUYRESDXCVBNNHGDFSEWRTGASDFGHJKUYTRESDDFVBGHJUYRESDXCVBNNHGDFSEWRTG";
	    private int jwtExpirationMs = 86400000; // 1 day

	    public String generateJwtToken(UserDetails userDetails) {
	        return Jwts.builder()
	                .setSubject(userDetails.getUsername())
	                .setIssuedAt(new Date())
	                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
	                .signWith(SignatureAlgorithm.HS512, jwtSecret)
	                .compact();
	    }

	    public String getUserNameFromJwtToken(String token) {
	        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
	    }

	    public boolean validateJwtToken(String authToken) {
	        try {
	            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
	            return true;
	        } catch (JwtException | IllegalArgumentException e) {
	            e.printStackTrace();
	        }
	        return false;
	    }
	    
	    public String getAuthenticatedUsername() {
	        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        if (authentication != null && authentication.isAuthenticated()) {
	            Object principal = authentication.getPrincipal();
	            
	            if (principal instanceof UserDetails) {
	                UserDetails userDetails = (UserDetails) principal;
	                 return userDetails.getUsername();
	                
	            }
	        }
	        throw new RuntimeException("User is not authenticated");
	    }
	}

