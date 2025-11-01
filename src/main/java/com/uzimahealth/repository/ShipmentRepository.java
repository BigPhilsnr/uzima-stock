package com.uzimahealth.repository;

import com.uzimahealth.stock.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
    Shipment findByShipmentNo(String shipmentNo);
}