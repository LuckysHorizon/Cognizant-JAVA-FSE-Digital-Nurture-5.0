package com.cognizant.jpa;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class SpringDataJpaQueriesApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDataJpaQueriesApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(ProductRepository productRepo) {
        return args -> {
            System.out.println("--- Bootstrapping Data (HQL and Native Query) ---");
            
            productRepo.saveAll(Arrays.asList(
                new Product("Laptop", 1200.0, "Electronics"),
                new Product("Smartphone", 800.0, "Electronics"),
                new Product("Headphones", 150.0, "Electronics"),
                new Product("Office Chair", 250.0, "Furniture"),
                new Product("Standing Desk", 450.0, "Furniture")
            ));

            System.out.println("--- Executing HQL Query: Electronics over $500 ---");
            productRepo.findByCategoryAndMinimumPriceHql("Electronics", 500.0)
                       .forEach(System.out::println);

            System.out.println("--- Executing Native Query: Search name 'Desk' ordered by price DESC ---");
            productRepo.searchByNameNativeQuery("Desk")
                       .forEach(System.out::println);

            System.out.println("--- Executing HQL Aggregate Query: Average price of Electronics ---");
            Double avgPrice = productRepo.calculateAveragePriceByCategoryHql("Electronics");
            System.out.println("Average Price: $" + String.format("%.2f", avgPrice));
            
            System.out.println("Application execution completed successfully.");
        };
    }
}
