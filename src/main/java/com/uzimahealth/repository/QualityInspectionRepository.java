package com.uzimahealth.repository;

import com.uzimahealth.stock.QualityInspection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QualityInspectionRepository extends JpaRepository<QualityInspection, Long> {
    List<QualityInspection> findByReferenceDocumentTypeAndReferenceDocumentId(String referenceDocumentType, Long referenceDocumentId);
    List<QualityInspection> findByItemId(Long itemId);
}