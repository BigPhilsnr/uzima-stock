package com.uzimahealth.repository;

import com.uzimahealth.stock.DeliveryNoteItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeliveryNoteItemRepository extends JpaRepository<DeliveryNoteItem, Long> {
    List<DeliveryNoteItem> findByDeliveryNoteId(Long deliveryNoteId);
}