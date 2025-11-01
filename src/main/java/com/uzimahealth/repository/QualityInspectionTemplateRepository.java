package com.uzimahealth.repository;

import com.uzimahealth.stock.QualityInspectionTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QualityInspectionTemplateRepository extends JpaRepository<QualityInspectionTemplate, Long> {
    QualityInspectionTemplate findByName(String name);
}