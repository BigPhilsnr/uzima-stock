package com.uzimahealth.controller.transactions.procurement;

import com.uzimahealth.stock.MaterialRequest;
import com.uzimahealth.stock.MaterialRequestItem;
import com.uzimahealth.repository.MaterialRequestRepository;
import com.uzimahealth.repository.MaterialRequestItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/material-requests")
public class MaterialRequestController {

    @Autowired
    private MaterialRequestRepository materialRequestRepository;

    @Autowired
    private MaterialRequestItemRepository materialRequestItemRepository;

    // Material Request endpoints
    @GetMapping
    public ResponseEntity<List<MaterialRequest>> getAllMaterialRequests() {
        return ResponseEntity.ok(materialRequestRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaterialRequest> getMaterialRequest(@PathVariable Long id) {
        MaterialRequest request = materialRequestRepository.findById(id).orElse(null);
        if (request != null) {
            return ResponseEntity.ok(request);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<MaterialRequest> createMaterialRequest(@RequestBody MaterialRequest request) {
        return ResponseEntity.ok(materialRequestRepository.save(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MaterialRequest> updateMaterialRequest(@PathVariable Long id, @RequestBody MaterialRequest request) {
        MaterialRequest existingRequest = materialRequestRepository.findById(id).orElse(null);
        if (existingRequest != null) {
            request.setId(id);
            return ResponseEntity.ok(materialRequestRepository.save(request));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMaterialRequest(@PathVariable Long id) {
        MaterialRequest request = materialRequestRepository.findById(id).orElse(null);
        if (request != null) {
            materialRequestRepository.delete(request);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Material Request Item endpoints
    @GetMapping("/{requestId}/items")
    public ResponseEntity<List<MaterialRequestItem>> getMaterialRequestItems(@PathVariable Long requestId) {
        return ResponseEntity.ok(materialRequestItemRepository.findByMaterialRequestId(requestId));
    }

    @PostMapping("/{requestId}/items")
    public ResponseEntity<MaterialRequestItem> addMaterialRequestItem(@PathVariable Long requestId, @RequestBody MaterialRequestItem item) {
        MaterialRequest request = materialRequestRepository.findById(requestId).orElse(null);
        if (request != null) {
            item.setMaterialRequest(request);
            return ResponseEntity.ok(materialRequestItemRepository.save(item));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{requestId}/items/{itemId}")
    public ResponseEntity<MaterialRequestItem> updateMaterialRequestItem(@PathVariable Long requestId, @PathVariable Long itemId, @RequestBody MaterialRequestItem item) {
        MaterialRequestItem existingItem = materialRequestItemRepository.findById(itemId).orElse(null);
        if (existingItem != null && existingItem.getMaterialRequest().getId().equals(requestId)) {
            item.setId(itemId);
            item.setMaterialRequest(existingItem.getMaterialRequest());
            return ResponseEntity.ok(materialRequestItemRepository.save(item));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{requestId}/items/{itemId}")
    public ResponseEntity<Void> deleteMaterialRequestItem(@PathVariable Long requestId, @PathVariable Long itemId) {
        MaterialRequestItem item = materialRequestItemRepository.findById(itemId).orElse(null);
        if (item != null && item.getMaterialRequest().getId().equals(requestId)) {
            materialRequestItemRepository.delete(item);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}