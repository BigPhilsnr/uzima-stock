package com.uzimahealth.controller.transactions;

import com.uzimahealth.stock.StockReconciliation;
import com.uzimahealth.stock.StockReconciliationItem;
import com.uzimahealth.repository.StockReconciliationRepository;
import com.uzimahealth.repository.StockReconciliationItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stock-reconciliations")
public class StockReconciliationController {

    @Autowired
    private StockReconciliationRepository stockReconciliationRepository;

    @Autowired
    private StockReconciliationItemRepository stockReconciliationItemRepository;

    // Stock Reconciliation endpoints
    @GetMapping
    public ResponseEntity<List<StockReconciliation>> getAllStockReconciliations() {
        return ResponseEntity.ok(stockReconciliationRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StockReconciliation> getStockReconciliation(@PathVariable Long id) {
        StockReconciliation reconciliation = stockReconciliationRepository.findById(id).orElse(null);
        if (reconciliation != null) {
            return ResponseEntity.ok(reconciliation);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<StockReconciliation> createStockReconciliation(@RequestBody StockReconciliation reconciliation) {
        return ResponseEntity.ok(stockReconciliationRepository.save(reconciliation));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StockReconciliation> updateStockReconciliation(@PathVariable Long id, @RequestBody StockReconciliation reconciliation) {
        StockReconciliation existingReconciliation = stockReconciliationRepository.findById(id).orElse(null);
        if (existingReconciliation != null) {
            reconciliation.setId(id);
            return ResponseEntity.ok(stockReconciliationRepository.save(reconciliation));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStockReconciliation(@PathVariable Long id) {
        StockReconciliation reconciliation = stockReconciliationRepository.findById(id).orElse(null);
        if (reconciliation != null) {
            stockReconciliationRepository.delete(reconciliation);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Stock Reconciliation Item endpoints
    @GetMapping("/{reconciliationId}/items")
    public ResponseEntity<List<StockReconciliationItem>> getStockReconciliationItems(@PathVariable Long reconciliationId) {
        return ResponseEntity.ok(stockReconciliationItemRepository.findByStockReconciliationId(reconciliationId));
    }

    @PostMapping("/{reconciliationId}/items")
    public ResponseEntity<StockReconciliationItem> addStockReconciliationItem(@PathVariable Long reconciliationId, @RequestBody StockReconciliationItem item) {
        StockReconciliation reconciliation = stockReconciliationRepository.findById(reconciliationId).orElse(null);
        if (reconciliation != null) {
            item.setStockReconciliation(reconciliation);
            return ResponseEntity.ok(stockReconciliationItemRepository.save(item));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{reconciliationId}/items/{itemId}")
    public ResponseEntity<StockReconciliationItem> updateStockReconciliationItem(@PathVariable Long reconciliationId, @PathVariable Long itemId, @RequestBody StockReconciliationItem item) {
        StockReconciliationItem existingItem = stockReconciliationItemRepository.findById(itemId).orElse(null);
        if (existingItem != null && existingItem.getStockReconciliation().getId().equals(reconciliationId)) {
            item.setId(itemId);
            item.setStockReconciliation(existingItem.getStockReconciliation());
            return ResponseEntity.ok(stockReconciliationItemRepository.save(item));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{reconciliationId}/items/{itemId}")
    public ResponseEntity<Void> deleteStockReconciliationItem(@PathVariable Long reconciliationId, @PathVariable Long itemId) {
        StockReconciliationItem item = stockReconciliationItemRepository.findById(itemId).orElse(null);
        if (item != null && item.getStockReconciliation().getId().equals(reconciliationId)) {
            stockReconciliationItemRepository.delete(item);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Stock Reconciliation by warehouse
    @GetMapping("/warehouse/{warehouseCode}")
    public ResponseEntity<List<StockReconciliation>> getStockReconciliationsByWarehouse(@PathVariable String warehouseCode) {
        return ResponseEntity.ok(stockReconciliationRepository.findByWarehouse(warehouseCode));
    }
}