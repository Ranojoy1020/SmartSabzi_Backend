package com.rbanerjee.SmartSabzi.Entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Entity
public class SaleItem {

    @Id
    @Column
    @GeneratedValue
    @UuidGenerator
    private UUID saleItemId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sale_id", nullable = false)
    private Sale sale;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "vendor_vegetable_id", nullable = false)
    private VendorVegetable vendorVegetable;

    @Column
    private double weight;

    @Column
    private BigDecimal unitPrice;

    @Column
    private BigDecimal itemTotal;
}
