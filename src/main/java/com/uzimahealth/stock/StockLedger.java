package com.uzimahealth.stock;

import com.uzimahealth.model.User;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class StockLedger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;
    private String transactionType; // Receipt, Issue, Return, Adjustment
    private int quantity;
    private String batchNumber;
    private LocalDateTime transactionDate;
    private String reference; // PO number, Prescription ID, etc.
    private String store;
    @ManyToOne
    @JoinColumn(name = "performed_by")
    private User performedBy;
    private String notes;

    public StockLedger() {}

    public StockLedger(Item item, String transactionType, int quantity, String batchNumber,
                       String reference, String store, User performedBy, String notes) {
        this.item = item;
        this.transactionType = transactionType;
        this.quantity = quantity;
        this.batchNumber = batchNumber;
        this.reference = reference;
        this.store = store;
        this.performedBy = performedBy;
        this.notes = notes;
        this.transactionDate = LocalDateTime.now();
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Item getItem() { return item; }
    public void setItem(Item item) { this.item = item; }
    public String getTransactionType() { return transactionType; }
    public void setTransactionType(String transactionType) { this.transactionType = transactionType; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public String getBatchNumber() { return batchNumber; }
    public void setBatchNumber(String batchNumber) { this.batchNumber = batchNumber; }
    public LocalDateTime getTransactionDate() { return transactionDate; }
    public void setTransactionDate(LocalDateTime transactionDate) { this.transactionDate = transactionDate; }
    public String getReference() { return reference; }
    public void setReference(String reference) { this.reference = reference; }
    public String getStore() { return store; }
    public void setStore(String store) { this.store = store; }
    public User getPerformedBy() { return performedBy; }
    public void setPerformedBy(User performedBy) { this.performedBy = performedBy; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}