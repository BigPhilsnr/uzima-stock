package com.uzimahealth.repository;

import com.uzimahealth.stock.StockReconciliationItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockReconciliationItemRepository extends JpaRepository<StockReconciliationItem, Long> {
}