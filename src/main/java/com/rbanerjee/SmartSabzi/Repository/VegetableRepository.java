package com.rbanerjee.SmartSabzi.Repository;

import com.rbanerjee.SmartSabzi.Entity.Vegetable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VegetableRepository extends JpaRepository<Vegetable, UUID> {
}
