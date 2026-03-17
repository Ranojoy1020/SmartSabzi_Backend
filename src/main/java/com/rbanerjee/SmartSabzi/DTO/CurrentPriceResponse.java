package com.rbanerjee.SmartSabzi.DTO;

import com.rbanerjee.SmartSabzi.Entity.Price;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record CurrentPriceResponse(UUID priceId,
                                   UUID VendorVegetableId,
                                   UUID vegetableId,
                                   String vegetableName,
                                   BigDecimal pricePerKg,
                                   Instant effectiveFrom) {

    public static CurrentPriceResponse fromEntity(Price price){
        return new CurrentPriceResponse(
                price.getPriceId(),
                price.getVendorVegetable().getVendorVegetableID(),
                price.getVendorVegetable().getVegetable().getVegetableId(),
                price.getVendorVegetable().getVegetable().getVegetableName(),
                price.getPricePerKg(),
                price.getEffectiveFrom()
        );
    }
}
