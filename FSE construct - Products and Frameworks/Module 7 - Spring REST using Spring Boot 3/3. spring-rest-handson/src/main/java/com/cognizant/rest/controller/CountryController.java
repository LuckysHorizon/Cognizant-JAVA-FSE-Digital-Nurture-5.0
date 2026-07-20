package com.cognizant.rest.controller;

import com.cognizant.rest.exception.CountryNotFoundException;
import com.cognizant.rest.model.Country;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/countries")
public class CountryController {

    private final List<Country> countries;

    public CountryController() {
        countries = new ArrayList<>();
        countries.add(new Country("IN", "India"));
        countries.add(new Country("US", "United States"));
    }

    @GetMapping
    public List<Country> getAllCountries() {
        return countries;
    }

    @GetMapping("/{code}")
    public ResponseEntity<Country> getCountry(@PathVariable String code) {
        Country country = countries.stream()
                .filter(c -> c.getCode().equalsIgnoreCase(code))
                .findFirst()
                .orElseThrow(() -> new CountryNotFoundException("Country with code " + code + " not found"));
        return ResponseEntity.ok(country);
    }

    @PostMapping
    public ResponseEntity<Country> addCountry(@Valid @RequestBody Country country) {
        countries.add(country);
        return ResponseEntity.status(HttpStatus.CREATED).body(country);
    }

    @PutMapping("/{code}")
    public ResponseEntity<Country> updateCountry(@PathVariable String code, @Valid @RequestBody Country updatedCountry) {
        Country existingCountry = countries.stream()
                .filter(c -> c.getCode().equalsIgnoreCase(code))
                .findFirst()
                .orElseThrow(() -> new CountryNotFoundException("Country with code " + code + " not found"));
        
        existingCountry.setName(updatedCountry.getName());
        return ResponseEntity.ok(existingCountry);
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Void> deleteCountry(@PathVariable String code) {
        boolean removed = countries.removeIf(c -> c.getCode().equalsIgnoreCase(code));
        if (!removed) {
            throw new CountryNotFoundException("Country with code " + code + " not found for deletion");
        }
        return ResponseEntity.noContent().build();
    }
}
