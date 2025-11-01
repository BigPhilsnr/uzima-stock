package com.uzimahealth.repository;

import com.uzimahealth.stock.MaterialRequestItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterialRequestItemRepository extends JpaRepository<MaterialRequestItem, Long> {
}