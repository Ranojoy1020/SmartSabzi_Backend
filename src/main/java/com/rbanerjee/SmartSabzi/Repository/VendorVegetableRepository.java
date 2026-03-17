package com.rbanerjee.SmartSabzi.Repository;

import com.rbanerjee.SmartSabzi.Entity.VendorVegetable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VendorVegetableRepository extends JpaRepository<VendorVegetable, UUID> {
    Optional<List<VendorVegetable>> findAllByVendorEmail(String  vendorId);
}
