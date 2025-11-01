package com.uzimahealth.repository;

import com.uzimahealth.stock.SerialNo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SerialNoRepository extends JpaRepository<SerialNo, Long> {
    Optional<SerialNo> findBySerialNo(String serialNo);
    List<SerialNo> findByItemCode(String itemCode);
}