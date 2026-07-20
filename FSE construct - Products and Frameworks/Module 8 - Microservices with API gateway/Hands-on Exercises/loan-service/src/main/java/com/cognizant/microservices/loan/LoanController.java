package com.cognizant.microservices.loan;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loans")
public class LoanController {

    @GetMapping("/{loanId}")
    public String getLoanDetails(@PathVariable String loanId) {
        return "Loan Details for ID: " + loanId + " (Served by Loan-Service)";
    }
}
