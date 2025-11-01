package com.uzimahealth.stock;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class StockReconciliationItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "stock_reconciliation_id")
    private StockReconciliation stockReconciliation;
    private String itemCode;
    private String itemName;
    private String warehouse;
    private BigDecimal qty;
    private BigDecimal valuationRate;
    private BigDecimal amount;
    private String serialNo;
    private String batchNo;
    private LocalDateTime expiryDate;

    public StockReconciliationItem() {}

    public StockReconciliationItem(StockReconciliation stockReconciliation, String itemCode, String itemName,
                                   String warehouse, BigDecimal qty, BigDecimal valuationRate, BigDecimal amount,
                                   String serialNo, String batchNo, LocalDateTime expiryDate) {
        this.stockReconciliation = stockReconciliation;
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.warehouse = warehouse;
        this.qty = qty;
        this.valuationRate = valuationRate;
        this.amount = amount;
        this.serialNo = serialNo;
        this.batchNo = batchNo;
        this.expiryDate = expiryDate;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public StockReconciliation getStockReconciliation() { return stockReconciliation; }
    public void setStockReconciliation(StockReconciliation stockReconciliation) { this.stockReconciliation = stockReconciliation; }
    public String getItemCode() { return itemCode; }
    public void setItemCode(String itemCode) { this.itemCode = itemCode; }
    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }
    public String getWarehouse() { return warehouse; }
    public void setWarehouse(String warehouse) { this.warehouse = warehouse; }
    public BigDecimal getQty() { return qty; }
    public void setQty(BigDecimal qty) { this.qty = qty; }
    public BigDecimal getValuationRate() { return valuationRate; }
    public void setValuationRate(BigDecimal valuationRate) { this.valuationRate = valuationRate; }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public String getSerialNo() { return serialNo; }
    public void setSerialNo(String serialNo) { this.serialNo = serialNo; }
    public String getBatchNo() { return batchNo; }
    public void setBatchNo(String batchNo) { this.batchNo = batchNo; }
    public LocalDateTime getExpiryDate() { return expiryDate; }
    public void setExpiryDate(LocalDateTime expiryDate) { this.expiryDate = expiryDate; }
}