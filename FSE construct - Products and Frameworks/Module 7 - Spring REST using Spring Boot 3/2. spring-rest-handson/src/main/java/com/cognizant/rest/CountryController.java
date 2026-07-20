package com.cognizant.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CountryController {

    private final List<Country> countries;

    public CountryController() {
        countries = new ArrayList<>();
        countries.add(new Country("IN", "India"));
        countries.add(new Country("US", "United States"));
        countries.add(new Country("DE", "Germany"));
        countries.add(new Country("JP", "Japan"));
    }

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello World";
    }

    @GetMapping("/countries")
    public List<Country> getAllCountries() {
        return countries;
    }

    @GetMapping("/countries/{code}")
    public ResponseEntity<Country> getCountryByCode(@PathVariable String code) {
        Optional<Country> country = countries.stream()
                .filter(c -> c.getCode().equalsIgnoreCase(code))
                .findFirst();

        return country.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
