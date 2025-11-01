package com.uzimahealth.stock;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Batch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String batchId;
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;
    private String itemCode; // For querying
    private LocalDate expiryDate;
    private LocalDate manufacturingDate;
    private String supplier;
    private String description;
    private LocalDateTime creation;
    private LocalDateTime modified;

    public Batch() {}

    public Batch(String batchId, Item item, String itemCode, LocalDate expiryDate, LocalDate manufacturingDate,
                 String supplier, String description) {
        this.batchId = batchId;
        this.item = item;
        this.itemCode = itemCode;
        this.expiryDate = expiryDate;
        this.manufacturingDate = manufacturingDate;
        this.supplier = supplier;
        this.description = description;
        this.creation = LocalDateTime.now();
        this.modified = LocalDateTime.now();
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getBatchId() { return batchId; }
    public void setBatchId(String batchId) { this.batchId = batchId; }
    public Item getItem() { return item; }
    public void setItem(Item item) { this.item = item; }
    public String getItemCode() { return itemCode; }
    public void setItemCode(String itemCode) { this.itemCode = itemCode; }
    public LocalDate getExpiryDate() { return expiryDate; }
    public void setExpiryDate(LocalDate expiryDate) { this.expiryDate = expiryDate; }
    public LocalDate getManufacturingDate() { return manufacturingDate; }
    public void setManufacturingDate(LocalDate manufacturingDate) { this.manufacturingDate = manufacturingDate; }
    public String getSupplier() { return supplier; }
    public void setSupplier(String supplier) { this.supplier = supplier; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public LocalDateTime getCreation() { return creation; }
    public void setCreation(LocalDateTime creation) { this.creation = creation; }
    public LocalDateTime getModified() { return modified; }
    public void setModified(LocalDateTime modified) { this.modified = modified; }
}