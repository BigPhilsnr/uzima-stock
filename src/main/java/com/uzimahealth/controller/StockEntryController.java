package com.uzimahealth.controller;

import com.uzimahealth.stock.StockEntry;
import com.uzimahealth.stock.StockEntryDetail;
import com.uzimahealth.repository.StockEntryRepository;
import com.uzimahealth.repository.StockEntryDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stock-entries")
public class StockEntryController {

    @Autowired
    private StockEntryRepository stockEntryRepository;

    @Autowired
    private StockEntryDetailRepository stockEntryDetailRepository;

    // Stock Entry endpoints
    @GetMapping
    public ResponseEntity<List<StockEntry>> getAllStockEntries() {
        return ResponseEntity.ok(stockEntryRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StockEntry> getStockEntry(@PathVariable Long id) {
        StockEntry entry = stockEntryRepository.findById(id).orElse(null);
        if (entry != null) {
            return ResponseEntity.ok(entry);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<StockEntry> createStockEntry(@RequestBody StockEntry entry) {
        return ResponseEntity.ok(stockEntryRepository.save(entry));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StockEntry> updateStockEntry(@PathVariable Long id, @RequestBody StockEntry entry) {
        StockEntry existingEntry = stockEntryRepository.findById(id).orElse(null);
        if (existingEntry != null) {
            entry.setId(id);
            return ResponseEntity.ok(stockEntryRepository.save(entry));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStockEntry(@PathVariable Long id) {
        StockEntry entry = stockEntryRepository.findById(id).orElse(null);
        if (entry != null) {
            stockEntryRepository.delete(entry);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Stock Entry Detail endpoints
    @GetMapping("/{entryId}/details")
    public ResponseEntity<List<StockEntryDetail>> getStockEntryDetails(@PathVariable Long entryId) {
        return ResponseEntity.ok(stockEntryDetailRepository.findByStockEntryId(entryId));
    }

    @PostMapping("/{entryId}/details")
    public ResponseEntity<StockEntryDetail> addStockEntryDetail(@PathVariable Long entryId, @RequestBody StockEntryDetail detail) {
        StockEntry entry = stockEntryRepository.findById(entryId).orElse(null);
        if (entry != null) {
            detail.setStockEntry(entry);
            return ResponseEntity.ok(stockEntryDetailRepository.save(detail));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{entryId}/details/{detailId}")
    public ResponseEntity<StockEntryDetail> updateStockEntryDetail(@PathVariable Long entryId, @PathVariable Long detailId, @RequestBody StockEntryDetail detail) {
        StockEntryDetail existingDetail = stockEntryDetailRepository.findById(detailId).orElse(null);
        if (existingDetail != null && existingDetail.getStockEntry().getId().equals(entryId)) {
            detail.setId(detailId);
            detail.setStockEntry(existingDetail.getStockEntry());
            return ResponseEntity.ok(stockEntryDetailRepository.save(detail));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{entryId}/details/{detailId}")
    public ResponseEntity<Void> deleteStockEntryDetail(@PathVariable Long entryId, @PathVariable Long detailId) {
        StockEntryDetail detail = stockEntryDetailRepository.findById(detailId).orElse(null);
        if (detail != null && detail.getStockEntry().getId().equals(entryId)) {
            stockEntryDetailRepository.delete(detail);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Stock Entry by type
    @GetMapping("/type/{entryType}")
    public ResponseEntity<List<StockEntry>> getStockEntriesByType(@PathVariable String entryType) {
        return ResponseEntity.ok(stockEntryRepository.findByEntryType(entryType));
    }
}