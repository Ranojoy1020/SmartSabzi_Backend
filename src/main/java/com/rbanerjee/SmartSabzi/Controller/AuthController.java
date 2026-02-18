package com.rbanerjee.SmartSabzi.Controller;

import com.rbanerjee.SmartSabzi.DTO.LoginRequest;
import com.rbanerjee.SmartSabzi.DTO.LoginResponse;
import com.rbanerjee.SmartSabzi.DTO.SignupRequest;
import com.rbanerjee.SmartSabzi.Entity.Vendor;
import com.rbanerjee.SmartSabzi.Service.AuthService;
import com.rbanerjee.SmartSabzi.Service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class AuthController {
    private final JwtService jwtService;
    private final AuthService authService;

    AuthController(JwtService jwtService, AuthService authService){
        this.jwtService = jwtService;
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<Vendor> signup(@RequestBody SignupRequest signupRequest){
        Vendor vendor = authService.signUp(signupRequest);
        return ResponseEntity.ok(vendor);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){
        Vendor vendor = authService.login(loginRequest);
        String jwtToken = jwtService.generateToken(vendor);
        LoginResponse loginResponse = new LoginResponse(jwtToken, jwtService.getExpirationTime());
        return ResponseEntity.ok(loginResponse);
    }
}
