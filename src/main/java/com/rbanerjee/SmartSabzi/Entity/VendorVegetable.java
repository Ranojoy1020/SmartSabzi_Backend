package com.rbanerjee.SmartSabzi.Entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"vendor_id", "vegetable_id"})
        }
)
public class VendorVegetable {

    @Id
    @Column
    @GeneratedValue
    @UuidGenerator
    private UUID vendorVegetableID;

    @JoinColumn(name = "vendor_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Vendor vendor;

    @JoinColumn(name = "vegetable_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Vegetable vegetable;

    @Column
    private boolean isActive;

    @Column(updatable = false)
    @CreationTimestamp
    private Instant createdAt;

    @Column
    @UpdateTimestamp
    private Instant updatedAt;

    @OneToMany(mappedBy = "vendorVegetable", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Price> priceList;
}
