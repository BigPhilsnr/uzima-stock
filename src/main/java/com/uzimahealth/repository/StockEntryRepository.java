package com.uzimahealth.repository;

import com.uzimahealth.stock.StockEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockEntryRepository extends JpaRepository<StockEntry, Long> {
    List<StockEntry> findByStockEntryType(String stockEntryType);
}