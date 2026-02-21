package com.rbanerjee.SmartSabzi.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Vegetable {

    @Id
    @Column
    @GeneratedValue
    @UuidGenerator
    private UUID vegetableId;

    @Column(unique = true, nullable = false)
    @NotNull
    private String vegetableName;

    @OneToMany(mappedBy = "vegetable", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<VendorVegetable> vendorVegetableList;

    @Column(updatable = false)
    @CreationTimestamp
    private Instant createdAt;
}
