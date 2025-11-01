package com.uzimahealth.repository;

import com.uzimahealth.stock.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
    Optional<Warehouse> findByWarehouseName(String warehouseName);
    Optional<Warehouse> findByWarehouseCode(String warehouseCode);
}