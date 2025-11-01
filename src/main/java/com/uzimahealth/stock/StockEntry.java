package com.uzimahealth.stock;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class StockEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String stockEntryNo;
    private String stockEntryType; // Material Receipt, Material Issue, Material Transfer, Manufacture
    private String purpose;
    private String fromWarehouse;
    private String toWarehouse;
    private LocalDateTime postingDate;
    private LocalDateTime postingTime;
    private boolean inspectionRequired = false;
    private String company;
    @OneToMany(mappedBy = "stockEntry", cascade = CascadeType.ALL)
    private List<StockEntryDetail> items;
    private LocalDateTime creation;
    private LocalDateTime modified;

    public StockEntry() {}

    public StockEntry(String stockEntryNo, String stockEntryType, String purpose, String fromWarehouse,
                      String toWarehouse, LocalDateTime postingDate, LocalDateTime postingTime,
                      boolean inspectionRequired, String company, List<StockEntryDetail> items) {
        this.stockEntryNo = stockEntryNo;
        this.stockEntryType = stockEntryType;
        this.purpose = purpose;
        this.fromWarehouse = fromWarehouse;
        this.toWarehouse = toWarehouse;
        this.postingDate = postingDate;
        this.postingTime = postingTime;
        this.inspectionRequired = inspectionRequired;
        this.company = company;
        this.items = items;
        this.creation = LocalDateTime.now();
        this.modified = LocalDateTime.now();
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getStockEntryNo() { return stockEntryNo; }
    public void setStockEntryNo(String stockEntryNo) { this.stockEntryNo = stockEntryNo; }
    public String getStockEntryType() { return stockEntryType; }
    public void setStockEntryType(String stockEntryType) { this.stockEntryType = stockEntryType; }
    public String getPurpose() { return purpose; }
    public void setPurpose(String purpose) { this.purpose = purpose; }
    public String getFromWarehouse() { return fromWarehouse; }
    public void setFromWarehouse(String fromWarehouse) { this.fromWarehouse = fromWarehouse; }
    public String getToWarehouse() { return toWarehouse; }
    public void setToWarehouse(String toWarehouse) { this.toWarehouse = toWarehouse; }
    public LocalDateTime getPostingDate() { return postingDate; }
    public void setPostingDate(LocalDateTime postingDate) { this.postingDate = postingDate; }
    public LocalDateTime getPostingTime() { return postingTime; }
    public void setPostingTime(LocalDateTime postingTime) { this.postingTime = postingTime; }
    public boolean isInspectionRequired() { return inspectionRequired; }
    public void setInspectionRequired(boolean inspectionRequired) { this.inspectionRequired = inspectionRequired; }
    public String getCompany() { return company; }
    public void setCompany(String company) { this.company = company; }
    public List<StockEntryDetail> getItems() { return items; }
    public void setItems(List<StockEntryDetail> items) { this.items = items; }
    public LocalDateTime getCreation() { return creation; }
    public void setCreation(LocalDateTime creation) { this.creation = creation; }
    public LocalDateTime getModified() { return modified; }
    public void setModified(LocalDateTime modified) { this.modified = modified; }
}