package com.rbanerjee.SmartSabzi.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Data
public class Price {

    @Id
    @Column
    @GeneratedValue
    @UuidGenerator
    private UUID priceId;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "vendor_vegetable", nullable = false)
    private VendorVegetable vendorVegetable;

    @Column
    @NotNull
    private BigDecimal pricePerKg;

    @Column
    @NotNull
    private Instant effectiveFrom;

    @Column(updatable = false)
    @CreationTimestamp
    private Instant createdAt;
}
