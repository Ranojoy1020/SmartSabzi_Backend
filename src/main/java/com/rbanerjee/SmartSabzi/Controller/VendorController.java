package com.rbanerjee.SmartSabzi.Controller;

import com.rbanerjee.SmartSabzi.DTO.CurrentVendorProfile;
import com.rbanerjee.SmartSabzi.DTO.NewCatalogResponse;
import com.rbanerjee.SmartSabzi.Entity.Vegetable;
import com.rbanerjee.SmartSabzi.Entity.VendorVegetable;
import com.rbanerjee.SmartSabzi.Service.VendorService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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

    @PostMapping("/catalog")
    public ResponseEntity<NewCatalogResponse> addToCatalog(Authentication authentication, @RequestBody Vegetable vegetable){
        VendorVegetable vendorVegetable = vendorService.addToCatalog(authentication.getName(), vegetable.getVegetableId());
        NewCatalogResponse newCatalogResponse = NewCatalogResponse.fromEntity(vendorVegetable);
        return new ResponseEntity<>(newCatalogResponse, HttpStatusCode.valueOf(201));
    }

}
