package com.rbanerjee.SmartSabzi.DTO;

import java.util.List;

public record CreateSaleRequest(
        List<CreateSaleItemRequest> items
) {
}
