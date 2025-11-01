package com.uzimahealth.stock;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class MaterialRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String materialRequestNo;
    private String materialRequestType; // Purchase, Material Transfer, Material Issue, Manufacture, Customer Provided
    private String status;
    private LocalDateTime transactionDate;
    private LocalDateTime scheduleDate;
    private String company;
    @OneToMany(mappedBy = "materialRequest", cascade = CascadeType.ALL)
    private List<MaterialRequestItem> items;
    private LocalDateTime creation;
    private LocalDateTime modified;

    public MaterialRequest() {}

    public MaterialRequest(String materialRequestNo, String materialRequestType, String status,
                           LocalDateTime transactionDate, LocalDateTime scheduleDate, String company,
                           List<MaterialRequestItem> items) {
        this.materialRequestNo = materialRequestNo;
        this.materialRequestType = materialRequestType;
        this.status = status;
        this.transactionDate = transactionDate;
        this.scheduleDate = scheduleDate;
        this.company = company;
        this.items = items;
        this.creation = LocalDateTime.now();
        this.modified = LocalDateTime.now();
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getMaterialRequestNo() { return materialRequestNo; }
    public void setMaterialRequestNo(String materialRequestNo) { this.materialRequestNo = materialRequestNo; }
    public String getMaterialRequestType() { return materialRequestType; }
    public void setMaterialRequestType(String materialRequestType) { this.materialRequestType = materialRequestType; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDateTime getTransactionDate() { return transactionDate; }
    public void setTransactionDate(LocalDateTime transactionDate) { this.transactionDate = transactionDate; }
    public LocalDateTime getScheduleDate() { return scheduleDate; }
    public void setScheduleDate(LocalDateTime scheduleDate) { this.scheduleDate = scheduleDate; }
    public String getCompany() { return company; }
    public void setCompany(String company) { this.company = company; }
    public List<MaterialRequestItem> getItems() { return items; }
    public void setItems(List<MaterialRequestItem> items) { this.items = items; }
    public LocalDateTime getCreation() { return creation; }
    public void setCreation(LocalDateTime creation) { this.creation = creation; }
    public LocalDateTime getModified() { return modified; }
    public void setModified(LocalDateTime modified) { this.modified = modified; }
}