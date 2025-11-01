package com.uzimahealth.repository;

import com.uzimahealth.stock.PurchaseReceiptItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseReceiptItemRepository extends JpaRepository<PurchaseReceiptItem, Long> {
    List<PurchaseReceiptItem> findByPurchaseReceiptId(Long purchaseReceiptId);
}