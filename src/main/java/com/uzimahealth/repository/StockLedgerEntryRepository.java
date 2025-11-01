package com.uzimahealth.repository;

import com.uzimahealth.stock.StockLedgerEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockLedgerEntryRepository extends JpaRepository<StockLedgerEntry, Long> {
    List<StockLedgerEntry> findByItemCode(String itemCode);
    List<StockLedgerEntry> findByItemCodeAndWarehouseCode(String itemCode, String warehouseCode);
}