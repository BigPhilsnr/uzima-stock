package com.uzimahealth.controller.transactions;

import com.uzimahealth.stock.Shipment;
import com.uzimahealth.repository.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shipments")
public class ShipmentController {

    @Autowired
    private ShipmentRepository shipmentRepository;

    @GetMapping
    public List<Shipment> getAllShipments() {
        return shipmentRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Shipment> getShipmentById(@PathVariable Long id) {
        return shipmentRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Shipment createShipment(@RequestBody Shipment shipment) {
        return shipmentRepository.save(shipment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Shipment> updateShipment(@PathVariable Long id, @RequestBody Shipment shipmentDetails) {
        return shipmentRepository.findById(id)
                .map(shipment -> {
                    shipment.setShipmentNo(shipmentDetails.getShipmentNo());
                    shipment.setShipmentType(shipmentDetails.getShipmentType());
                    shipment.setPickupFrom(shipmentDetails.getPickupFrom());
                    shipment.setDeliverTo(shipmentDetails.getDeliverTo());
                    shipment.setCarrier(shipmentDetails.getCarrier());
                    shipment.setVehicleNo(shipmentDetails.getVehicleNo());
                    shipment.setDriver(shipmentDetails.getDriver());
                    shipment.setDriverPhone(shipmentDetails.getDriverPhone());
                    shipment.setRemarks(shipmentDetails.getRemarks());
                    shipment.setItems(shipmentDetails.getItems());
                    shipment.setStatus(shipmentDetails.getStatus());
                    shipment.setPickupDate(shipmentDetails.getPickupDate());
                    shipment.setDeliveryDate(shipmentDetails.getDeliveryDate());
                    shipment.setUpdatedAt(java.time.LocalDateTime.now());
                    return ResponseEntity.ok(shipmentRepository.save(shipment));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShipment(@PathVariable Long id) {
        if (shipmentRepository.existsById(id)) {
            shipmentRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}