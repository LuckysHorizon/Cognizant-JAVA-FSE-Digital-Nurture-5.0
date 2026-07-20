package com.cognizant.jwt.controller;

import com.cognizant.jwt.model.AuthRequest;
import com.cognizant.jwt.model.AuthResponse;
import com.cognizant.jwt.util.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthController {

    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthRequest authRequest) {
        // Mock authentication validation (in reality this would check DB)
        if ("admin".equals(authRequest.getUsername()) && "password".equals(authRequest.getPassword())) {
            String jwt = jwtUtil.generateToken(authRequest.getUsername());
            return ResponseEntity.ok(new AuthResponse(jwt));
        }
        
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
    }

    // Secured endpoint to verify token flow
    @GetMapping("/secured-hello")
    public ResponseEntity<String> securedEndpoint() {
        return ResponseEntity.ok("Hello from secured endpoint!");
    }
}
