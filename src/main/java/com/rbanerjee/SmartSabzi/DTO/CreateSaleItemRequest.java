package com.rbanerjee.SmartSabzi.DTO;

import java.math.BigDecimal;
import java.util.UUID;

public record CreateSaleItemRequest(
        UUID vendorVegetableId,
        double weight,
        BigDecimal unitPrice
) {
}
