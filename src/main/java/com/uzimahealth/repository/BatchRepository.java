package com.uzimahealth.repository;

import com.uzimahealth.stock.Batch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BatchRepository extends JpaRepository<Batch, Long> {
}