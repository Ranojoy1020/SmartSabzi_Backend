package com.rbanerjee.SmartSabzi.DTO;

import com.rbanerjee.SmartSabzi.Entity.Price;
import com.rbanerjee.SmartSabzi.Entity.Vegetable;
import com.rbanerjee.SmartSabzi.Entity.Vendor;
import com.rbanerjee.SmartSabzi.Entity.VendorVegetable;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record NewCatalogResponse(UUID vendorVegetableId,
                                    UUID vendorId,
                                    UUID vegetableId,
                                    String vegetableName,
                                    boolean isActive,
                                    List<Price> priceList,
                                    Instant createdAt,
                                    Instant updatedAt) {
    public static NewCatalogResponse fromEntity(VendorVegetable vendorVegetable){
        return new NewCatalogResponse(
                vendorVegetable.getVendorVegetableID(),
                vendorVegetable.getVendor().getVendorId(),
                vendorVegetable.getVegetable().getVegetableId(),
                vendorVegetable.getVegetable().getVegetableName(),
                vendorVegetable.isActive(),
                vendorVegetable.getPriceList(),
                vendorVegetable.getCreatedAt(),
                vendorVegetable.getUpdatedAt()
        );
    }
}
