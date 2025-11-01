package com.uzimahealth.repository;

import com.uzimahealth.stock.LandedCostVoucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LandedCostVoucherRepository extends JpaRepository<LandedCostVoucher, Long> {
    LandedCostVoucher findByVoucherNo(String voucherNo);
}