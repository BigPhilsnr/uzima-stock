package com.uzimahealth.repository;

import com.uzimahealth.stock.DeliveryTrip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryTripRepository extends JpaRepository<DeliveryTrip, Long> {
    DeliveryTrip findByTripNo(String tripNo);
}