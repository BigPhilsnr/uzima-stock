package com.uzimahealth.repository;

import com.uzimahealth.stock.PurchaseReceiptItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseReceiptItemRepository extends JpaRepository<PurchaseReceiptItem, Long> {
}