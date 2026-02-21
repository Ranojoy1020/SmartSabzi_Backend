package com.rbanerjee.SmartSabzi.Service;

import com.rbanerjee.SmartSabzi.DTO.CurrentVendorProfile;
import com.rbanerjee.SmartSabzi.Entity.Vegetable;
import com.rbanerjee.SmartSabzi.Entity.Vendor;
import com.rbanerjee.SmartSabzi.Entity.VendorVegetable;
import com.rbanerjee.SmartSabzi.Repository.VegetableRepository;
import com.rbanerjee.SmartSabzi.Repository.VendorRepository;
import com.rbanerjee.SmartSabzi.Repository.VendorVegetableRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class VendorService {
    private final VendorRepository vendorRepository;
    private final VegetableRepository vegetableRepository;
    private  final VendorVegetableRepository vendorVegetableRepository;

    VendorService(VendorRepository vendorRepository,
                  VegetableRepository vegetableRepository,
                  VendorVegetableRepository vendorVegetableRepository){
        this.vendorRepository = vendorRepository;
        this.vegetableRepository = vegetableRepository;
        this.vendorVegetableRepository = vendorVegetableRepository;
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

    public VendorVegetable addToCatalog(String email, UUID vegetable_id){
        Vendor vendor = vendorRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Vendor Not Found"));
        System.out.println(vegetable_id);
        Vegetable vegetable = vegetableRepository.findById(vegetable_id)
                .orElseThrow(() -> new RuntimeException("Vegetable Not Found"));

        VendorVegetable newVendorVegetable = new VendorVegetable();
        newVendorVegetable.setVendor(vendor);
        newVendorVegetable.setVegetable(vegetable);
        newVendorVegetable.setActive(true);

        return vendorVegetableRepository.save(newVendorVegetable);
    }
}
