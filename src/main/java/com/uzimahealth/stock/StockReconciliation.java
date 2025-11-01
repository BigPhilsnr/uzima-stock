package com.uzimahealth.stock;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class StockReconciliation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String stockReconciliationNo;
    private String warehouse;
    private LocalDateTime postingDate;
    private LocalDateTime postingTime;
    private String company;
    private String purpose;
    @OneToMany(mappedBy = "stockReconciliation", cascade = CascadeType.ALL)
    private List<StockReconciliationItem> items;
    private LocalDateTime creation;
    private LocalDateTime modified;

    public StockReconciliation() {}

    public StockReconciliation(String stockReconciliationNo, String warehouse, LocalDateTime postingDate,
                               LocalDateTime postingTime, String company, String purpose,
                               List<StockReconciliationItem> items) {
        this.stockReconciliationNo = stockReconciliationNo;
        this.warehouse = warehouse;
        this.postingDate = postingDate;
        this.postingTime = postingTime;
        this.company = company;
        this.purpose = purpose;
        this.items = items;
        this.creation = LocalDateTime.now();
        this.modified = LocalDateTime.now();
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getStockReconciliationNo() { return stockReconciliationNo; }
    public void setStockReconciliationNo(String stockReconciliationNo) { this.stockReconciliationNo = stockReconciliationNo; }
    public String getWarehouse() { return warehouse; }
    public void setWarehouse(String warehouse) { this.warehouse = warehouse; }
    public LocalDateTime getPostingDate() { return postingDate; }
    public void setPostingDate(LocalDateTime postingDate) { this.postingDate = postingDate; }
    public LocalDateTime getPostingTime() { return postingTime; }
    public void setPostingTime(LocalDateTime postingTime) { this.postingTime = postingTime; }
    public String getCompany() { return company; }
    public void setCompany(String company) { this.company = company; }
    public String getPurpose() { return purpose; }
    public void setPurpose(String purpose) { this.purpose = purpose; }
    public List<StockReconciliationItem> getItems() { return items; }
    public void setItems(List<StockReconciliationItem> items) { this.items = items; }
    public LocalDateTime getCreation() { return creation; }
    public void setCreation(LocalDateTime creation) { this.creation = creation; }
    public LocalDateTime getModified() { return modified; }
    public void setModified(LocalDateTime modified) { this.modified = modified; }
}