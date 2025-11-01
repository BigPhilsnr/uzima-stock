package com.uzimahealth.repository;

import com.uzimahealth.stock.StockReconciliation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockReconciliationRepository extends JpaRepository<StockReconciliation, Long> {
    StockReconciliation findByStockReconciliationNo(String stockReconciliationNo);
    List<StockReconciliation> findByWarehouseCode(String warehouseCode);
}