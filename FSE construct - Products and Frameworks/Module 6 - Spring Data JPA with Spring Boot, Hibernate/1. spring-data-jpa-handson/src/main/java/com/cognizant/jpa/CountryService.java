package com.cognizant.jpa;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CountryService {

    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Transactional
    public Country addCountry(Country country) {
        return countryRepository.save(country);
    }

    public Country findCountryByCode(String code) {
        return countryRepository.findByCode(code)
                .orElseThrow(() -> new IllegalArgumentException("Country with code " + code + " not found"));
    }

    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }
}
