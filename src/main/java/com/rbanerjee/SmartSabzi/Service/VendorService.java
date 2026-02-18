package com.rbanerjee.SmartSabzi.Service;

import com.rbanerjee.SmartSabzi.DTO.CurrentVendorProfile;
import com.rbanerjee.SmartSabzi.Entity.Vendor;
import com.rbanerjee.SmartSabzi.Repository.VendorRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class VendorService {
    private final VendorRepository vendorRepository;

    VendorService(VendorRepository vendorRepository){
        this.vendorRepository = vendorRepository;
    }

    public CurrentVendorProfile getCurrentVendor(String  email){

        Vendor vendor = vendorRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        return new CurrentVendorProfile(
                vendor.getEmail(),
                vendor.getShopName(),
                vendor.getCreatedAt().toString()
        );
    }
}
