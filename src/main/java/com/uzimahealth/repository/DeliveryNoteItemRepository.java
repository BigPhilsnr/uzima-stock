package com.uzimahealth.repository;

import com.uzimahealth.stock.DeliveryNoteItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryNoteItemRepository extends JpaRepository<DeliveryNoteItem, Long> {
}