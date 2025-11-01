package com.uzimahealth.repository;

import com.uzimahealth.stock.SerialNo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SerialNoRepository extends JpaRepository<SerialNo, Long> {
}