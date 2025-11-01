package com.uzimahealth.controller.transactions.procurement;

import com.uzimahealth.stock.PurchaseReceipt;
import com.uzimahealth.stock.PurchaseReceiptItem;
import com.uzimahealth.repository.PurchaseReceiptRepository;
import com.uzimahealth.repository.PurchaseReceiptItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/purchase-receipts")
public class PurchaseReceiptController {

    @Autowired
    private PurchaseReceiptRepository purchaseReceiptRepository;

    @Autowired
    private PurchaseReceiptItemRepository purchaseReceiptItemRepository;

    // Purchase Receipt endpoints
    @GetMapping
    public ResponseEntity<List<PurchaseReceipt>> getAllPurchaseReceipts() {
        return ResponseEntity.ok(purchaseReceiptRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PurchaseReceipt> getPurchaseReceipt(@PathVariable Long id) {
        PurchaseReceipt receipt = purchaseReceiptRepository.findById(id).orElse(null);
        if (receipt != null) {
            return ResponseEntity.ok(receipt);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<PurchaseReceipt> createPurchaseReceipt(@RequestBody PurchaseReceipt receipt) {
        return ResponseEntity.ok(purchaseReceiptRepository.save(receipt));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PurchaseReceipt> updatePurchaseReceipt(@PathVariable Long id, @RequestBody PurchaseReceipt receipt) {
        PurchaseReceipt existingReceipt = purchaseReceiptRepository.findById(id).orElse(null);
        if (existingReceipt != null) {
            receipt.setId(id);
            return ResponseEntity.ok(purchaseReceiptRepository.save(receipt));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePurchaseReceipt(@PathVariable Long id) {
        PurchaseReceipt receipt = purchaseReceiptRepository.findById(id).orElse(null);
        if (receipt != null) {
            purchaseReceiptRepository.delete(receipt);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Purchase Receipt Item endpoints
    @GetMapping("/{receiptId}/items")
    public ResponseEntity<List<PurchaseReceiptItem>> getPurchaseReceiptItems(@PathVariable Long receiptId) {
        return ResponseEntity.ok(purchaseReceiptItemRepository.findByPurchaseReceiptId(receiptId));
    }

    @PostMapping("/{receiptId}/items")
    public ResponseEntity<PurchaseReceiptItem> addPurchaseReceiptItem(@PathVariable Long receiptId, @RequestBody PurchaseReceiptItem item) {
        PurchaseReceipt receipt = purchaseReceiptRepository.findById(receiptId).orElse(null);
        if (receipt != null) {
            item.setPurchaseReceipt(receipt);
            return ResponseEntity.ok(purchaseReceiptItemRepository.save(item));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{receiptId}/items/{itemId}")
    public ResponseEntity<PurchaseReceiptItem> updatePurchaseReceiptItem(@PathVariable Long receiptId, @PathVariable Long itemId, @RequestBody PurchaseReceiptItem item) {
        PurchaseReceiptItem existingItem = purchaseReceiptItemRepository.findById(itemId).orElse(null);
        if (existingItem != null && existingItem.getPurchaseReceipt().getId().equals(receiptId)) {
            item.setId(itemId);
            item.setPurchaseReceipt(existingItem.getPurchaseReceipt());
            return ResponseEntity.ok(purchaseReceiptItemRepository.save(item));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{receiptId}/items/{itemId}")
    public ResponseEntity<Void> deletePurchaseReceiptItem(@PathVariable Long receiptId, @PathVariable Long itemId) {
        PurchaseReceiptItem item = purchaseReceiptItemRepository.findById(itemId).orElse(null);
        if (item != null && item.getPurchaseReceipt().getId().equals(receiptId)) {
            purchaseReceiptItemRepository.delete(item);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}