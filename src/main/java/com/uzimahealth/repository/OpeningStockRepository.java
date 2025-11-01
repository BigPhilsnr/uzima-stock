package com.uzimahealth.repository;

import com.uzimahealth.stock.OpeningStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpeningStockRepository extends JpaRepository<OpeningStock, Long> {
    OpeningStock findByOpeningStockNo(String openingStockNo);
}