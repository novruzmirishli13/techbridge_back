package com.example.HZT;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HztApplication {

    public static void main(String[] args) {
        // Portu doğrudan 8080 olarak ayarladık
        int port = 8080;

        System.out.println("Uygulama portu: " + port);
        SpringApplication.run(HztApplication.class, args);
    }
}
