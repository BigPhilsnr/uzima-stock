package com.uzimahealth.stock;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class SerialNo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String serialNo;
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;
    private String itemCode; // For querying
    private String warehouse;
    private String batchNo;
    private String status; // Active, Inactive, Delivered, etc.
    private String purchaseDocumentType;
    private String purchaseDocumentNo;
    private LocalDateTime purchaseDate;
    private String deliveryDocumentType;
    private String deliveryDocumentNo;
    private LocalDateTime deliveryDate;
    private String company;
    private LocalDateTime creation;
    private LocalDateTime modified;

    public SerialNo() {}

    public SerialNo(String serialNo, Item item, String itemCode, String warehouse, String batchNo, String status,
                    String purchaseDocumentType, String purchaseDocumentNo, LocalDateTime purchaseDate,
                    String deliveryDocumentType, String deliveryDocumentNo, LocalDateTime deliveryDate,
                    String company) {
        this.serialNo = serialNo;
        this.item = item;
        this.itemCode = itemCode;
        this.warehouse = warehouse;
        this.batchNo = batchNo;
        this.status = status;
        this.purchaseDocumentType = purchaseDocumentType;
        this.purchaseDocumentNo = purchaseDocumentNo;
        this.purchaseDate = purchaseDate;
        this.deliveryDocumentType = deliveryDocumentType;
        this.deliveryDocumentNo = deliveryDocumentNo;
        this.deliveryDate = deliveryDate;
        this.company = company;
        this.creation = LocalDateTime.now();
        this.modified = LocalDateTime.now();
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getSerialNo() { return serialNo; }
    public void setSerialNo(String serialNo) { this.serialNo = serialNo; }
    public Item getItem() { return item; }
    public void setItem(Item item) { this.item = item; }
    public String getItemCode() { return itemCode; }
    public void setItemCode(String itemCode) { this.itemCode = itemCode; }
    public String getWarehouse() { return warehouse; }
    public void setWarehouse(String warehouse) { this.warehouse = warehouse; }
    public String getBatchNo() { return batchNo; }
    public void setBatchNo(String batchNo) { this.batchNo = batchNo; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getPurchaseDocumentType() { return purchaseDocumentType; }
    public void setPurchaseDocumentType(String purchaseDocumentType) { this.purchaseDocumentType = purchaseDocumentType; }
    public String getPurchaseDocumentNo() { return purchaseDocumentNo; }
    public void setPurchaseDocumentNo(String purchaseDocumentNo) { this.purchaseDocumentNo = purchaseDocumentNo; }
    public LocalDateTime getPurchaseDate() { return purchaseDate; }
    public void setPurchaseDate(LocalDateTime purchaseDate) { this.purchaseDate = purchaseDate; }
    public String getDeliveryDocumentType() { return deliveryDocumentType; }
    public void setDeliveryDocumentType(String deliveryDocumentType) { this.deliveryDocumentType = deliveryDocumentType; }
    public String getDeliveryDocumentNo() { return deliveryDocumentNo; }
    public void setDeliveryDocumentNo(String deliveryDocumentNo) { this.deliveryDocumentNo = deliveryDocumentNo; }
    public LocalDateTime getDeliveryDate() { return deliveryDate; }
    public void setDeliveryDate(LocalDateTime deliveryDate) { this.deliveryDate = deliveryDate; }
    public String getCompany() { return company; }
    public void setCompany(String company) { this.company = company; }
    public LocalDateTime getCreation() { return creation; }
    public void setCreation(LocalDateTime creation) { this.creation = creation; }
    public LocalDateTime getModified() { return modified; }
    public void setModified(LocalDateTime modified) { this.modified = modified; }
}