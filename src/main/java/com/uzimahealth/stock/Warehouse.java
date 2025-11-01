package com.uzimahealth.stock;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String warehouseName;
    private boolean isGroup = false;
    private String parentWarehouse;
    private String company;
    private String account;
    private String warehouseType;
    private boolean disabled = false;
    private LocalDateTime creation;
    private LocalDateTime modified;

    public Warehouse() {}

    public Warehouse(String warehouseName, boolean isGroup, String parentWarehouse, String company,
                     String account, String warehouseType, boolean disabled) {
        this.warehouseName = warehouseName;
        this.isGroup = isGroup;
        this.parentWarehouse = parentWarehouse;
        this.company = company;
        this.account = account;
        this.warehouseType = warehouseType;
        this.disabled = disabled;
        this.creation = LocalDateTime.now();
        this.modified = LocalDateTime.now();
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getWarehouseName() { return warehouseName; }
    public void setWarehouseName(String warehouseName) { this.warehouseName = warehouseName; }
    public boolean isGroup() { return isGroup; }
    public void setGroup(boolean group) { isGroup = group; }
    public String getParentWarehouse() { return parentWarehouse; }
    public void setParentWarehouse(String parentWarehouse) { this.parentWarehouse = parentWarehouse; }
    public String getCompany() { return company; }
    public void setCompany(String company) { this.company = company; }
    public String getAccount() { return account; }
    public void setAccount(String account) { this.account = account; }
    public String getWarehouseType() { return warehouseType; }
    public void setWarehouseType(String warehouseType) { this.warehouseType = warehouseType; }
    public boolean isDisabled() { return disabled; }
    public void setDisabled(boolean disabled) { this.disabled = disabled; }
    public LocalDateTime getCreation() { return creation; }
    public void setCreation(LocalDateTime creation) { this.creation = creation; }
    public LocalDateTime getModified() { return modified; }
    public void setModified(LocalDateTime modified) { this.modified = modified; }
}