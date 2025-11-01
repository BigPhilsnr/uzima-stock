package com.uzimahealth.controller;

import com.uzimahealth.stock.PackingSlip;
import com.uzimahealth.repository.PackingSlipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/packing-slips")
public class PackingSlipController {

    @Autowired
    private PackingSlipRepository packingSlipRepository;

    @GetMapping
    public List<PackingSlip> getAllPackingSlips() {
        return packingSlipRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PackingSlip> getPackingSlipById(@PathVariable Long id) {
        return packingSlipRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public PackingSlip createPackingSlip(@RequestBody PackingSlip packingSlip) {
        return packingSlipRepository.save(packingSlip);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PackingSlip> updatePackingSlip(@PathVariable Long id, @RequestBody PackingSlip slipDetails) {
        return packingSlipRepository.findById(id)
                .map(slip -> {
                    slip.setPackingSlipNo(slipDetails.getPackingSlipNo());
                    slip.setDeliveryNoteNo(slipDetails.getDeliveryNoteNo());
                    slip.setCustomer(slipDetails.getCustomer());
                    slip.setPackingDetails(slipDetails.getPackingDetails());
                    slip.setItems(slipDetails.getItems());
                    slip.setStatus(slipDetails.getStatus());
                    slip.setPackedDate(slipDetails.getPackedDate());
                    slip.setUpdatedAt(java.time.LocalDateTime.now());
                    return ResponseEntity.ok(packingSlipRepository.save(slip));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePackingSlip(@PathVariable Long id) {
        if (packingSlipRepository.existsById(id)) {
            packingSlipRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}