package com.uzimahealth.controller;

import com.uzimahealth.stock.Stock;
import com.uzimahealth.stock.StockLedgerEntry;
import com.uzimahealth.repository.StockRepository;
import com.uzimahealth.repository.StockLedgerEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stock")
public class StockController {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private StockLedgerEntryRepository stockLedgerEntryRepository;

    // Stock levels endpoints
    @GetMapping("/levels")
    public ResponseEntity<List<Stock>> getAllStockLevels() {
        return ResponseEntity.ok(stockRepository.findAll());
    }

    @GetMapping("/levels/{itemCode}/{warehouseCode}")
    public ResponseEntity<Stock> getStockLevel(@PathVariable String itemCode, @PathVariable String warehouseCode) {
        Stock stock = stockRepository.findByItemCodeAndWarehouseCode(itemCode, warehouseCode).orElse(null);
        if (stock != null) {
            return ResponseEntity.ok(stock);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/levels/item/{itemCode}")
    public ResponseEntity<List<Stock>> getStockLevelsByItem(@PathVariable String itemCode) {
        return ResponseEntity.ok(stockRepository.findByItemCode(itemCode));
    }

    @GetMapping("/levels/warehouse/{warehouseCode}")
    public ResponseEntity<List<Stock>> getStockLevelsByWarehouse(@PathVariable String warehouseCode) {
        return ResponseEntity.ok(stockRepository.findByWarehouseCode(warehouseCode));
    }

    // Stock ledger endpoints
    @GetMapping("/ledger")
    public ResponseEntity<List<StockLedgerEntry>> getAllStockLedgerEntries() {
        return ResponseEntity.ok(stockLedgerEntryRepository.findAll());
    }

    @GetMapping("/ledger/{itemCode}")
    public ResponseEntity<List<StockLedgerEntry>> getStockLedgerByItem(@PathVariable String itemCode) {
        return ResponseEntity.ok(stockLedgerEntryRepository.findByItemCode(itemCode));
    }

    @GetMapping("/ledger/{itemCode}/{warehouseCode}")
    public ResponseEntity<List<StockLedgerEntry>> getStockLedgerByItemAndWarehouse(
            @PathVariable String itemCode, @PathVariable String warehouseCode) {
        return ResponseEntity.ok(stockLedgerEntryRepository.findByItemCodeAndWarehouseCode(itemCode, warehouseCode));
    }

    @PostMapping("/ledger")
    public ResponseEntity<StockLedgerEntry> createStockLedgerEntry(@RequestBody StockLedgerEntry entry) {
        return ResponseEntity.ok(stockLedgerEntryRepository.save(entry));
    }
}