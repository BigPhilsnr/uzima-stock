package com.uzimahealth.stock;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;
    private String batchNumber;
    private LocalDate expiryDate;
    private int quantity;
    private String store; // Main Store, Pharmacy Store, Lab Store
    private double unitCost;

    public Stock() {}

    public Stock(Item item, String batchNumber, LocalDate expiryDate, int quantity, String store, double unitCost) {
        this.item = item;
        this.batchNumber = batchNumber;
        this.expiryDate = expiryDate;
        this.quantity = quantity;
        this.store = store;
        this.unitCost = unitCost;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Item getItem() { return item; }
    public void setItem(Item item) { this.item = item; }
    public String getBatchNumber() { return batchNumber; }
    public void setBatchNumber(String batchNumber) { this.batchNumber = batchNumber; }
    public LocalDate getExpiryDate() { return expiryDate; }
    public void setExpiryDate(LocalDate expiryDate) { this.expiryDate = expiryDate; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public String getStore() { return store; }
    public void setStore(String store) { this.store = store; }
    public double getUnitCost() { return unitCost; }
    public void setUnitCost(double unitCost) { this.unitCost = unitCost; }
}