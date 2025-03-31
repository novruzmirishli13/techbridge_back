package com.example.HZT;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HztApplication {

    public static void main(String[] args) {
        String portEnv = System.getenv("PORT");
        int port = 8081;

        if (portEnv != null && !portEnv.isEmpty()) {
            try {
                port = Integer.parseInt(portEnv);
            } catch (NumberFormatException e) {
                System.err.println("Geçersiz PORT değeri, varsayılan port kullanılacak: " + e.getMessage());
            }
        } else {
            System.out.println("PORT çevresel değişkeni bulunamadı, varsayılan port kullanılacak.");
        }

        System.out.println("Uygulama portu: " + port);
        SpringApplication.run(HztApplication.class, args);
    }
}