package com.rbanerjee.SmartSabzi.DTO;

import com.rbanerjee.SmartSabzi.Entity.Price;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record PriceResponse(UUID priceId,
                               UUID vendorVegetableId,
                               BigDecimal pricePerKg,
                               Instant effectiveFrom,
                               Instant createdAt) {

    public static PriceResponse fromEntity(Price price){
        return new PriceResponse(
                price.getPriceId(),
                price.getVendorVegetable().getVendorVegetableID(),
                price.getPricePerKg(),
                price.getEffectiveFrom(),
                price.getCreatedAt()
        );
    }
}
