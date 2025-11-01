package com.uzimahealth.repository;

import com.uzimahealth.stock.StockEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockEntryRepository extends JpaRepository<StockEntry, Long> {
}