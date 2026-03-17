package com.rbanerjee.SmartSabzi.Repository;

import com.rbanerjee.SmartSabzi.Entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SaleRepository extends JpaRepository<Sale, UUID> {

}
