package com.uzimahealth.repository;

import com.uzimahealth.stock.StockEntryDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockEntryDetailRepository extends JpaRepository<StockEntryDetail, Long> {
    List<StockEntryDetail> findByStockEntryId(Long stockEntryId);
}