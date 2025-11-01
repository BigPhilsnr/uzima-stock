package com.uzimahealth.repository;

import com.uzimahealth.stock.PackingSlip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PackingSlipRepository extends JpaRepository<PackingSlip, Long> {
    PackingSlip findByPackingSlipNo(String packingSlipNo);
}