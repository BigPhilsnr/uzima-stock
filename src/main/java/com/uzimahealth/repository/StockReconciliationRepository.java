package com.uzimahealth.repository;

import com.uzimahealth.stock.StockReconciliation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockReconciliationRepository extends JpaRepository<StockReconciliation, Long> {
    StockReconciliation findByStockReconciliationNo(String stockReconciliationNo);
}