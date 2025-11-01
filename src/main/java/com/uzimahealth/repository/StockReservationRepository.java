package com.uzimahealth.repository;

import com.uzimahealth.stock.StockReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockReservationRepository extends JpaRepository<StockReservation, Long> {
    List<StockReservation> findByItemId(Long itemId);
    List<StockReservation> findByWarehouseCode(String warehouseCode);
    StockReservation findByReservationNo(String reservationNo);
}