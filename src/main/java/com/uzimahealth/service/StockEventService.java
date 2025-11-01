package com.uzimahealth.service;

import com.uzimahealth.stock.*;
import com.uzimahealth.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class StockEventService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private StockLedgerEntryRepository stockLedgerEntryRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    public void handlePrescriptionDispensed(Map<String, Object> eventData) {
        // Extract prescription data from event
        String itemCode = (String) eventData.get("itemCode");
        BigDecimal quantity = new BigDecimal(eventData.get("quantity").toString());
        String warehouse = (String) eventData.get("warehouse");
        String user = (String) eventData.get("user");

        // Find item and warehouse
        Item item = itemRepository.findByItemCode(itemCode).orElse(null);
        Warehouse wh = warehouseRepository.findByWarehouseName(warehouse).orElse(null);

        if (item != null && wh != null) {
            // Create stock ledger entry for dispensing
            StockLedgerEntry entry = new StockLedgerEntry();
            entry.setItemCode(itemCode);
            entry.setWarehouse(warehouse);
            entry.setPostingDate(LocalDateTime.now());
            entry.setPostingTime(LocalDateTime.now());
            entry.setVoucherType("Prescription");
            entry.setVoucherNo((String) eventData.get("prescriptionNo"));
            entry.setStockUom(item.getStockUom());
            entry.setQtyOut(quantity);
            entry.setOutgoingRate(item.getValuationRate());
            entry.setValuationRate(item.getValuationRate());
            entry.setCompany("Uzima Health");
            entry.setFiscalYear("2025");
            entry.setIsCancelled(false);
            entry.setCreation(LocalDateTime.now());
            entry.setModified(LocalDateTime.now());

            stockLedgerEntryRepository.save(entry);
            System.out.println("Stock ledger entry created for prescription dispensing: " + itemCode);
        }
    }

    public void handleStockAdjustment(Map<String, Object> eventData) {
        // Handle stock adjustments from health app
        String itemCode = (String) eventData.get("itemCode");
        BigDecimal quantity = new BigDecimal(eventData.get("quantity").toString());
        String warehouse = (String) eventData.get("warehouse");
        String adjustmentType = (String) eventData.get("type"); // "in" or "out"

        Item item = itemRepository.findByItemCode(itemCode).orElse(null);
        Warehouse wh = warehouseRepository.findByWarehouseName(warehouse).orElse(null);

        if (item != null && wh != null) {
            StockLedgerEntry entry = new StockLedgerEntry();
            entry.setItemCode(itemCode);
            entry.setWarehouse(warehouse);
            entry.setPostingDate(LocalDateTime.now());
            entry.setPostingTime(LocalDateTime.now());
            entry.setVoucherType("Stock Adjustment");
            entry.setVoucherNo((String) eventData.get("adjustmentNo"));
            entry.setStockUom(item.getStockUom());
            entry.setValuationRate(item.getValuationRate());
            entry.setCompany("Uzima Health");
            entry.setFiscalYear("2025");
            entry.setIsCancelled(false);
            entry.setCreation(LocalDateTime.now());
            entry.setModified(LocalDateTime.now());

            if ("in".equals(adjustmentType)) {
                entry.setQtyIn(quantity);
                entry.setIncomingRate(item.getValuationRate());
            } else {
                entry.setQtyOut(quantity);
                entry.setOutgoingRate(item.getValuationRate());
            }

            stockLedgerEntryRepository.save(entry);
            System.out.println("Stock adjustment processed: " + itemCode + " - " + adjustmentType);
        }
    }
}