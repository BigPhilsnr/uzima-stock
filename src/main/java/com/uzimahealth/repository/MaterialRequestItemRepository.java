package com.uzimahealth.repository;

import com.uzimahealth.stock.MaterialRequestItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MaterialRequestItemRepository extends JpaRepository<MaterialRequestItem, Long> {
    List<MaterialRequestItem> findByMaterialRequestId(Long materialRequestId);
}