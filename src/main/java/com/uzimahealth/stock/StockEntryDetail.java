package com.uzimahealth.stock;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class StockEntryDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "stock_entry_id")
    private StockEntry stockEntry;
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;
    private String description;
    private double qty;
    private String uom;
    private String conversionFactor;
    private double transferQty;
    private double valuationRate;
    private double basicRate;
    private double basicAmount;
    private String batchNo;
    private LocalDate expiryDate;
    private String serialNo;
    private String qualityInspection;
    private String warehouse;

    public StockEntryDetail() {}

    public StockEntryDetail(StockEntry stockEntry, Item item, String description, double qty, String uom,
                            String conversionFactor, double transferQty, double valuationRate, double basicRate,
                            double basicAmount, String batchNo, LocalDate expiryDate, String serialNo,
                            String qualityInspection, String warehouse) {
        this.stockEntry = stockEntry;
        this.item = item;
        this.description = description;
        this.qty = qty;
        this.uom = uom;
        this.conversionFactor = conversionFactor;
        this.transferQty = transferQty;
        this.valuationRate = valuationRate;
        this.basicRate = basicRate;
        this.basicAmount = basicAmount;
        this.batchNo = batchNo;
        this.expiryDate = expiryDate;
        this.serialNo = serialNo;
        this.qualityInspection = qualityInspection;
        this.warehouse = warehouse;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public StockEntry getStockEntry() { return stockEntry; }
    public void setStockEntry(StockEntry stockEntry) { this.stockEntry = stockEntry; }
    public Item getItem() { return item; }
    public void setItem(Item item) { this.item = item; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public double getQty() { return qty; }
    public void setQty(double qty) { this.qty = qty; }
    public String getUom() { return uom; }
    public void setUom(String uom) { this.uom = uom; }
    public String getConversionFactor() { return conversionFactor; }
    public void setConversionFactor(String conversionFactor) { this.conversionFactor = conversionFactor; }
    public double getTransferQty() { return transferQty; }
    public void setTransferQty(double transferQty) { this.transferQty = transferQty; }
    public double getValuationRate() { return valuationRate; }
    public void setValuationRate(double valuationRate) { this.valuationRate = valuationRate; }
    public double getBasicRate() { return basicRate; }
    public void setBasicRate(double basicRate) { this.basicRate = basicRate; }
    public double getBasicAmount() { return basicAmount; }
    public void setBasicAmount(double basicAmount) { this.basicAmount = basicAmount; }
    public String getBatchNo() { return batchNo; }
    public void setBatchNo(String batchNo) { this.batchNo = batchNo; }
    public LocalDate getExpiryDate() { return expiryDate; }
    public void setExpiryDate(LocalDate expiryDate) { this.expiryDate = expiryDate; }
    public String getSerialNo() { return serialNo; }
    public void setSerialNo(String serialNo) { this.serialNo = serialNo; }
    public String getQualityInspection() { return qualityInspection; }
    public void setQualityInspection(String qualityInspection) { this.qualityInspection = qualityInspection; }
    public String getWarehouse() { return warehouse; }
    public void setWarehouse(String warehouse) { this.warehouse = warehouse; }
}