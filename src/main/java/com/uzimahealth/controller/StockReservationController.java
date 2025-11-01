package com.uzimahealth.controller;

import com.uzimahealth.stock.StockReservation;
import com.uzimahealth.repository.StockReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stock-reservations")
public class StockReservationController {

    @Autowired
    private StockReservationRepository stockReservationRepository;

    @GetMapping
    public List<StockReservation> getAllStockReservations() {
        return stockReservationRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StockReservation> getStockReservationById(@PathVariable Long id) {
        return stockReservationRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public StockReservation createStockReservation(@RequestBody StockReservation stockReservation) {
        return stockReservationRepository.save(stockReservation);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StockReservation> updateStockReservation(@PathVariable Long id, @RequestBody StockReservation reservationDetails) {
        return stockReservationRepository.findById(id)
                .map(reservation -> {
                    reservation.setReservationNo(reservationDetails.getReservationNo());
                    reservation.setItem(reservationDetails.getItem());
                    reservation.setWarehouseCode(reservationDetails.getWarehouseCode());
                    reservation.setReservedQty(reservationDetails.getReservedQty());
                    reservation.setBatchNo(reservationDetails.getBatchNo());
                    reservation.setSerialNo(reservationDetails.getSerialNo());
                    reservation.setReservedFor(reservationDetails.getReservedFor());
                    reservation.setReferenceNo(reservationDetails.getReferenceNo());
                    reservation.setStatus(reservationDetails.getStatus());
                    reservation.setExpiryDate(reservationDetails.getExpiryDate());
                    reservation.setUpdatedAt(java.time.LocalDateTime.now());
                    return ResponseEntity.ok(stockReservationRepository.save(reservation));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStockReservation(@PathVariable Long id) {
        if (stockReservationRepository.existsById(id)) {
            stockReservationRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}