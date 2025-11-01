package com.uzimahealth.repository;

import com.uzimahealth.stock.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Long> {
    Optional<Stock> findByItemCodeAndWarehouseCode(String itemCode, String warehouseCode);
    List<Stock> findByItemCode(String itemCode);
    List<Stock> findByWarehouseCode(String warehouseCode);
}