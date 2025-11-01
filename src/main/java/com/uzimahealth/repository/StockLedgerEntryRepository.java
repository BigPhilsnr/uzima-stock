package com.uzimahealth.repository;

import com.uzimahealth.stock.StockLedgerEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockLedgerEntryRepository extends JpaRepository<StockLedgerEntry, Long> {
}