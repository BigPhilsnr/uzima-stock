package com.uzimahealth.repository;

import com.uzimahealth.stock.DeliveryNote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryNoteRepository extends JpaRepository<DeliveryNote, Long> {
}