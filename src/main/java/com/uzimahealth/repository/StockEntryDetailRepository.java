package com.uzimahealth.repository;

import com.uzimahealth.stock.StockEntryDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockEntryDetailRepository extends JpaRepository<StockEntryDetail, Long> {
}