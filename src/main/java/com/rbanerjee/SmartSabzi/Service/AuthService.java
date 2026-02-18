package com.rbanerjee.SmartSabzi.Service;

import com.rbanerjee.SmartSabzi.DTO.LoginRequest;
import com.rbanerjee.SmartSabzi.DTO.SignupRequest;
import com.rbanerjee.SmartSabzi.Entity.Vendor;
import com.rbanerjee.SmartSabzi.Repository.VendorRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final VendorRepository vendorRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    AuthService(VendorRepository vendorRepository,
                PasswordEncoder passwordEncoder,
                AuthenticationManager authenticationManager){
        this.vendorRepository = vendorRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public Vendor signUp(SignupRequest signupRequest){
        Vendor vendor = new Vendor();
        vendor.setEmail(signupRequest.email());
        vendor.setPassword(passwordEncoder.encode(signupRequest.password()));
        vendor.setShopName(signupRequest.shopName());

        return vendorRepository.save(vendor);
    }

    public Vendor login(LoginRequest loginRequest){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.email(), loginRequest.password()
                )
        );

        return vendorRepository.findByEmail(loginRequest.email())
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
