package com.rbanerjee.SmartSabzi.DTO;

import com.rbanerjee.SmartSabzi.Entity.Price;
import com.rbanerjee.SmartSabzi.Entity.VendorVegetable;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record VendorCatalogResponse(UUID vendorVegetableId,
                                    String vegetableName,
                                    List<Price> priceList,
                                    Instant createdAt) {
    public static VendorCatalogResponse fromEntity(VendorVegetable vendorVegetable){
        return new VendorCatalogResponse(
                vendorVegetable.getVendorVegetableID(),
                vendorVegetable.getVegetable().getVegetableName(),
                vendorVegetable.getPriceList(),
                vendorVegetable.getCreatedAt());
    }
}
