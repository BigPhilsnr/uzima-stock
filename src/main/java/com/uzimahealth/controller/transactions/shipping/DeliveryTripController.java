package com.uzimahealth.controller.transactions.shipping;

import com.uzimahealth.stock.DeliveryTrip;
import com.uzimahealth.repository.DeliveryTripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/delivery-trips")
public class DeliveryTripController {

    @Autowired
    private DeliveryTripRepository deliveryTripRepository;

    @GetMapping
    public List<DeliveryTrip> getAllDeliveryTrips() {
        return deliveryTripRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeliveryTrip> getDeliveryTripById(@PathVariable Long id) {
        return deliveryTripRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public DeliveryTrip createDeliveryTrip(@RequestBody DeliveryTrip deliveryTrip) {
        return deliveryTripRepository.save(deliveryTrip);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DeliveryTrip> updateDeliveryTrip(@PathVariable Long id, @RequestBody DeliveryTrip tripDetails) {
        return deliveryTripRepository.findById(id)
                .map(trip -> {
                    trip.setTripNo(tripDetails.getTripNo());
                    trip.setDriver(tripDetails.getDriver());
                    trip.setVehicle(tripDetails.getVehicle());
                    trip.setRoute(tripDetails.getRoute());
                    trip.setStops(tripDetails.getStops());
                    trip.setStatus(tripDetails.getStatus());
                    trip.setDepartureDate(tripDetails.getDepartureDate());
                    trip.setReturnDate(tripDetails.getReturnDate());
                    trip.setUpdatedAt(java.time.LocalDateTime.now());
                    return ResponseEntity.ok(deliveryTripRepository.save(trip));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDeliveryTrip(@PathVariable Long id) {
        if (deliveryTripRepository.existsById(id)) {
            deliveryTripRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}