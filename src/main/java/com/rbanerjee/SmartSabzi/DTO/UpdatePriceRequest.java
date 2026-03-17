package com.rbanerjee.SmartSabzi.DTO;

import java.math.BigDecimal;
import java.util.UUID;

public record UpdatePriceRequest(UUID vendorVegetableId, BigDecimal pricePerKg){
}
