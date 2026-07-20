package com.cognizant.jpa;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringDataJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDataJpaApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(CountryService countryService) {
        return args -> {
            System.out.println("--- Adding Countries ---");
            countryService.addCountry(new Country("US", "United States"));
            countryService.addCountry(new Country("IN", "India"));
            countryService.addCountry(new Country("JP", "Japan"));

            System.out.println("--- All Countries in Database ---");
            countryService.getAllCountries().forEach(System.out::println);

            System.out.println("--- Finding Country by Code 'IN' ---");
            Country india = countryService.findCountryByCode("IN");
            System.out.println("Found: " + india);
            
            System.out.println("--- Finding Country by Code 'US' ---");
            Country usa = countryService.findCountryByCode("US");
            System.out.println("Found: " + usa);
            
            System.out.println("Application execution completed successfully.");
        };
    }
}
