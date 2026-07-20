package com.cognizant.microservices.account;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @GetMapping("/{accountNumber}")
    public String getAccountDetails(@PathVariable String accountNumber) {
        return "Account Details for ID: " + accountNumber + " (Served by Account-Service)";
    }
}
