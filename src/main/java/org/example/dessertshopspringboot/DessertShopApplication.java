package org.example.dessertshopspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ServletComponentScan
public class DessertShopApplication {
    public static void main(String[] args) {
        SpringApplication.run(DessertShopApplication.class, args);
    }
}
