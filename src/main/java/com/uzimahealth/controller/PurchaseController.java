package com.uzimahealth.controller;

import com.uzimahealth.stock.MaterialRequest;
import com.uzimahealth.stock.MaterialRequestItem;
import com.uzimahealth.stock.PurchaseReceipt;
import com.uzimahealth.stock.PurchaseReceiptItem;
import com.uzimahealth.repository.MaterialRequestRepository;
import com.uzimahealth.repository.MaterialRequestItemRepository;
import com.uzimahealth.repository.PurchaseReceiptRepository;
import com.uzimahealth.repository.PurchaseReceiptItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/purchase")
public class PurchaseController {

    @Autowired
    private MaterialRequestRepository materialRequestRepository;

    @Autowired
    private MaterialRequestItemRepository materialRequestItemRepository;

    @Autowired
    private PurchaseReceiptRepository purchaseReceiptRepository;

    @Autowired
    private PurchaseReceiptItemRepository purchaseReceiptItemRepository;

    // Material Request endpoints
    @GetMapping("/requests")
    public ResponseEntity<List<MaterialRequest>> getAllMaterialRequests() {
        return ResponseEntity.ok(materialRequestRepository.findAll());
    }

    @GetMapping("/requests/{id}")
    public ResponseEntity<MaterialRequest> getMaterialRequest(@PathVariable Long id) {
        MaterialRequest request = materialRequestRepository.findById(id).orElse(null);
        if (request != null) {
            return ResponseEntity.ok(request);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/requests")
    public ResponseEntity<MaterialRequest> createMaterialRequest(@RequestBody MaterialRequest request) {
        return ResponseEntity.ok(materialRequestRepository.save(request));
    }

    @GetMapping("/requests/{requestId}/items")
    public ResponseEntity<List<MaterialRequestItem>> getMaterialRequestItems(@PathVariable Long requestId) {
        return ResponseEntity.ok(materialRequestItemRepository.findByMaterialRequestId(requestId));
    }

    @PostMapping("/requests/{requestId}/items")
    public ResponseEntity<MaterialRequestItem> addMaterialRequestItem(@PathVariable Long requestId, @RequestBody MaterialRequestItem item) {
        MaterialRequest request = materialRequestRepository.findById(requestId).orElse(null);
        if (request != null) {
            item.setMaterialRequest(request);
            return ResponseEntity.ok(materialRequestItemRepository.save(item));
        }
        return ResponseEntity.notFound().build();
    }

    // Purchase Receipt endpoints
    @GetMapping("/receipts")
    public ResponseEntity<List<PurchaseReceipt>> getAllPurchaseReceipts() {
        return ResponseEntity.ok(purchaseReceiptRepository.findAll());
    }

    @GetMapping("/receipts/{id}")
    public ResponseEntity<PurchaseReceipt> getPurchaseReceipt(@PathVariable Long id) {
        PurchaseReceipt receipt = purchaseReceiptRepository.findById(id).orElse(null);
        if (receipt != null) {
            return ResponseEntity.ok(receipt);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/receipts")
    public ResponseEntity<PurchaseReceipt> createPurchaseReceipt(@RequestBody PurchaseReceipt receipt) {
        return ResponseEntity.ok(purchaseReceiptRepository.save(receipt));
    }

    @GetMapping("/receipts/{receiptId}/items")
    public ResponseEntity<List<PurchaseReceiptItem>> getPurchaseReceiptItems(@PathVariable Long receiptId) {
        return ResponseEntity.ok(purchaseReceiptItemRepository.findByPurchaseReceiptId(receiptId));
    }

    @PostMapping("/receipts/{receiptId}/items")
    public ResponseEntity<PurchaseReceiptItem> addPurchaseReceiptItem(@PathVariable Long receiptId, @RequestBody PurchaseReceiptItem item) {
        PurchaseReceipt receipt = purchaseReceiptRepository.findById(receiptId).orElse(null);
        if (receipt != null) {
            item.setPurchaseReceipt(receipt);
            return ResponseEntity.ok(purchaseReceiptItemRepository.save(item));
        }
        return ResponseEntity.notFound().build();
    }
}