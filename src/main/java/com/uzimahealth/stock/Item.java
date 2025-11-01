package com.uzimahealth.stock;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String itemCode;
    private String itemName;
    private String itemGroup;
    private String stockUom;
    private String description;
    private boolean isStockItem = true;
    private boolean hasVariants = false;
    private String variantOf;
    private String brand;
    private String manufacturer;
    private double standardRate;
    private double valuationRate;
    private int minOrderQty;
    private String shelfLifeInDays;
    private boolean isPurchaseItem = true;
    private boolean isSalesItem = true;
    private boolean disabled = false;
    private LocalDateTime creation;
    private LocalDateTime modified;

    public Item() {}

    public Item(String itemCode, String itemName, String itemGroup, String stockUom, String description,
                boolean isStockItem, boolean hasVariants, String variantOf, String brand, String manufacturer,
                double standardRate, double valuationRate, int minOrderQty, String shelfLifeInDays,
                boolean isPurchaseItem, boolean isSalesItem, boolean disabled) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.itemGroup = itemGroup;
        this.stockUom = stockUom;
        this.description = description;
        this.isStockItem = isStockItem;
        this.hasVariants = hasVariants;
        this.variantOf = variantOf;
        this.brand = brand;
        this.manufacturer = manufacturer;
        this.standardRate = standardRate;
        this.valuationRate = valuationRate;
        this.minOrderQty = minOrderQty;
        this.shelfLifeInDays = shelfLifeInDays;
        this.isPurchaseItem = isPurchaseItem;
        this.isSalesItem = isSalesItem;
        this.disabled = disabled;
        this.creation = LocalDateTime.now();
        this.modified = LocalDateTime.now();
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getItemCode() { return itemCode; }
    public void setItemCode(String itemCode) { this.itemCode = itemCode; }
    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }
    public String getItemGroup() { return itemGroup; }
    public void setItemGroup(String itemGroup) { this.itemGroup = itemGroup; }
    public String getStockUom() { return stockUom; }
    public void setStockUom(String stockUom) { this.stockUom = stockUom; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public boolean isStockItem() { return isStockItem; }
    public void setStockItem(boolean stockItem) { isStockItem = stockItem; }
    public boolean isHasVariants() { return hasVariants; }
    public void setHasVariants(boolean hasVariants) { this.hasVariants = hasVariants; }
    public String getVariantOf() { return variantOf; }
    public void setVariantOf(String variantOf) { this.variantOf = variantOf; }
    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }
    public String getManufacturer() { return manufacturer; }
    public void setManufacturer(String manufacturer) { this.manufacturer = manufacturer; }
    public double getStandardRate() { return standardRate; }
    public void setStandardRate(double standardRate) { this.standardRate = standardRate; }
    public double getValuationRate() { return valuationRate; }
    public void setValuationRate(double valuationRate) { this.valuationRate = valuationRate; }
    public int getMinOrderQty() { return minOrderQty; }
    public void setMinOrderQty(int minOrderQty) { this.minOrderQty = minOrderQty; }
    public String getShelfLifeInDays() { return shelfLifeInDays; }
    public void setShelfLifeInDays(String shelfLifeInDays) { this.shelfLifeInDays = shelfLifeInDays; }
    public boolean isPurchaseItem() { return isPurchaseItem; }
    public void setPurchaseItem(boolean purchaseItem) { isPurchaseItem = purchaseItem; }
    public boolean isSalesItem() { return isSalesItem; }
    public void setSalesItem(boolean salesItem) { isSalesItem = salesItem; }
    public boolean isDisabled() { return disabled; }
    public void setDisabled(boolean disabled) { this.disabled = disabled; }
    public LocalDateTime getCreation() { return creation; }
    public void setCreation(LocalDateTime creation) { this.creation = creation; }
    public LocalDateTime getModified() { return modified; }
    public void setModified(LocalDateTime modified) { this.modified = modified; }
}