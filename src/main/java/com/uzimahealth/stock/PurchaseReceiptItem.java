package com.uzimahealth.stock;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class PurchaseReceiptItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "purchase_receipt_id")
    private PurchaseReceipt purchaseReceipt;
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;
    private String description;
    private double qty;
    private String uom;
    private double rate;
    private double amount;
    private String batchNo;
    private LocalDate expiryDate;
    private String serialNo;
    private String warehouse;
    private String purchaseOrder;
    private String qualityInspection;

    public PurchaseReceiptItem() {}

    public PurchaseReceiptItem(PurchaseReceipt purchaseReceipt, Item item, String description, double qty,
                               String uom, double rate, double amount, String batchNo, LocalDate expiryDate,
                               String serialNo, String warehouse, String purchaseOrder, String qualityInspection) {
        this.purchaseReceipt = purchaseReceipt;
        this.item = item;
        this.description = description;
        this.qty = qty;
        this.uom = uom;
        this.rate = rate;
        this.amount = amount;
        this.batchNo = batchNo;
        this.expiryDate = expiryDate;
        this.serialNo = serialNo;
        this.warehouse = warehouse;
        this.purchaseOrder = purchaseOrder;
        this.qualityInspection = qualityInspection;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public PurchaseReceipt getPurchaseReceipt() { return purchaseReceipt; }
    public void setPurchaseReceipt(PurchaseReceipt purchaseReceipt) { this.purchaseReceipt = purchaseReceipt; }
    public Item getItem() { return item; }
    public void setItem(Item item) { this.item = item; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public double getQty() { return qty; }
    public void setQty(double qty) { this.qty = qty; }
    public String getUom() { return uom; }
    public void setUom(String uom) { this.uom = uom; }
    public double getRate() { return rate; }
    public void setRate(double rate) { this.rate = rate; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public String getBatchNo() { return batchNo; }
    public void setBatchNo(String batchNo) { this.batchNo = batchNo; }
    public LocalDate getExpiryDate() { return expiryDate; }
    public void setExpiryDate(LocalDate expiryDate) { this.expiryDate = expiryDate; }
    public String getSerialNo() { return serialNo; }
    public void setSerialNo(String serialNo) { this.serialNo = serialNo; }
    public String getWarehouse() { return warehouse; }
    public void setWarehouse(String warehouse) { this.warehouse = warehouse; }
    public String getPurchaseOrder() { return purchaseOrder; }
    public void setPurchaseOrder(String purchaseOrder) { this.purchaseOrder = purchaseOrder; }
    public String getQualityInspection() { return qualityInspection; }
    public void setQualityInspection(String qualityInspection) { this.qualityInspection = qualityInspection; }
}