package com.uzimahealth.repository;

import com.uzimahealth.stock.StockReconciliationItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockReconciliationItemRepository extends JpaRepository<StockReconciliationItem, Long> {
    List<StockReconciliationItem> findByStockReconciliationId(Long stockReconciliationId);
}