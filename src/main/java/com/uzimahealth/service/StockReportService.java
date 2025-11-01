package com.uzimahealth.service;

import com.uzimahealth.stock.StockLedgerEntry;
import com.uzimahealth.repository.StockLedgerEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StockReportService {

    @Autowired
    private StockLedgerEntryRepository stockLedgerEntryRepository;

    public List<StockLedgerEntry> getStockLedger(String itemCode, String warehouseCode, LocalDateTime fromDate, LocalDateTime toDate) {
        // Implement stock ledger report logic
        return stockLedgerEntryRepository.findAll().stream()
                .filter(entry -> (itemCode == null || entry.getItemCode().equals(itemCode)))
                .filter(entry -> (warehouseCode == null || entry.getWarehouse().equals(warehouseCode)))
                .filter(entry -> (fromDate == null || entry.getPostingDate().isAfter(fromDate) || entry.getPostingDate().isEqual(fromDate)))
                .filter(entry -> (toDate == null || entry.getPostingDate().isBefore(toDate) || entry.getPostingDate().isEqual(toDate)))
                .collect(Collectors.toList());
    }

    public Map<String, Double> getStockBalance(String warehouseCode) {
        // Implement stock balance report logic
        return stockLedgerEntryRepository.findAll().stream()
                .filter(entry -> warehouseCode == null || entry.getWarehouse().equals(warehouseCode))
                .collect(Collectors.groupingBy(
                        StockLedgerEntry::getItemCode,
                        Collectors.summingDouble(entry -> entry.getQtyIn().doubleValue() - entry.getQtyOut().doubleValue())
                ));
    }

    public List<Map<String, Object>> getStockValuation(String warehouseCode) {
        // Implement stock valuation report logic
        return stockLedgerEntryRepository.findAll().stream()
                .filter(entry -> warehouseCode == null || entry.getWarehouse().equals(warehouseCode))
                .collect(Collectors.groupingBy(StockLedgerEntry::getItemCode))
                .entrySet().stream()
                .map(entry -> {
                    double qty = entry.getValue().stream()
                            .mapToDouble(e -> e.getQtyIn().doubleValue() - e.getQtyOut().doubleValue())
                            .sum();
                    double rate = entry.getValue().stream()
                            .filter(e -> e.getValuationRate() != 0.0)
                            .mapToDouble(StockLedgerEntry::getValuationRate)
                            .average().orElse(0.0);
                    return Map.<String, Object>of(
                            "itemCode", entry.getKey(),
                            "quantity", qty,
                            "valuationRate", rate,
                            "totalValue", qty * rate
                    );
                })
                .collect(Collectors.toList());
    }
}