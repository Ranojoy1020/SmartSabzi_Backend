package com.rbanerjee.SmartSabzi.Controller;

import com.rbanerjee.SmartSabzi.DTO.CurrentVendorProfile;
import com.rbanerjee.SmartSabzi.Service.VendorService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/vendor")
@RestController
public class VendorController {

    private final VendorService vendorService;

    VendorController(VendorService vendorService){
        this.vendorService = vendorService;
    }

    @GetMapping("/profile")
    public ResponseEntity<CurrentVendorProfile> vendorProfile(Authentication authentication){
        CurrentVendorProfile currentVendorProfile = vendorService.getCurrentVendor(authentication.getName());
        return ResponseEntity.ok(currentVendorProfile);
    }

}
