package com.uzimahealth.repository;

import com.uzimahealth.stock.PurchaseReceipt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseReceiptRepository extends JpaRepository<PurchaseReceipt, Long> {
}