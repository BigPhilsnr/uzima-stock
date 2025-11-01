package com.uzimahealth.repository;

import com.uzimahealth.stock.InventoryDimension;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryDimensionRepository extends JpaRepository<InventoryDimension, Long> {
    InventoryDimension findByName(String name);
}