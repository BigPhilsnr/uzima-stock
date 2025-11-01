package com.uzimahealth.controller;

import com.uzimahealth.service.StockReportService;
import com.uzimahealth.stock.StockLedgerEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private StockReportService stockReportService;

    @GetMapping("/stock-ledger")
    public List<StockLedgerEntry> getStockLedger(
            @RequestParam(required = false) String itemCode,
            @RequestParam(required = false) String warehouseCode,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fromDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime toDate) {
        return stockReportService.getStockLedger(itemCode, warehouseCode, fromDate, toDate);
    }

    @GetMapping("/stock-balance")
    public Map<String, Double> getStockBalance(@RequestParam(required = false) String warehouseCode) {
        return stockReportService.getStockBalance(warehouseCode);
    }

    @GetMapping("/stock-valuation")
    public List<Map<String, Object>> getStockValuation(@RequestParam(required = false) String warehouseCode) {
        return stockReportService.getStockValuation(warehouseCode);
    }
}