package com.rbanerjee.SmartSabzi.Entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Sale {
    @Id
    @Column
    @GeneratedValue
    @UuidGenerator
    private UUID saleId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "vendor_id", nullable = false)
    private Vendor vendor;

    @Column
    private BigDecimal totalAmount;

    @Column(updatable = false)
    @CreationTimestamp
    private Instant createdAt;

    @OneToMany(mappedBy = "sale")
    private List<SaleItem> saleItemList;
}
