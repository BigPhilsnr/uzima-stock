package com.uzimahealth.repository;

import com.uzimahealth.stock.Batch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BatchRepository extends JpaRepository<Batch, Long> {
    Optional<Batch> findByBatchId(String batchId);
    List<Batch> findByItemCode(String itemCode);
}