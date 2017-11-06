package com.meneger;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(scanBasePackages = {"com.meneger"})
public class MenegerRestApplication {
    public static void main(String[] args) {
        SpringApplication.run(MenegerRestApplication.class,args);
    }

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
