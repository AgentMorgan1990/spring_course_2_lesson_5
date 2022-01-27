package com.flamexander.cloud.front.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.flamexander.cloud.common.ProductDto;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@RestController
@EnableEurekaClient
@Slf4j
public class FrontApp {

    @Autowired
    private RestTemplate restTemplate;
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(FrontApp.class, args);
    }


    @GetMapping("/api/v1/products")
    public List<ProductDto> getAllProductsFromProductService() {
        return restTemplate.getForObject("http://product-service/api/v1/products", List.class);
    }
}
