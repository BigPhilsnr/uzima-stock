package com.uzimahealth.stock;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class StockLedgerEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String itemCode;
    private String warehouse;
    private LocalDateTime postingDate;
    private LocalDateTime postingTime;
    private String voucherType;
    private String voucherNo;
    private String stockUom;
    private BigDecimal qtyIn;
    private BigDecimal qtyOut;
    private double incomingRate;
    private double outgoingRate;
    private double valuationRate;
    private String company;
    private String fiscalYear;
    private boolean isCancelled;
    private LocalDateTime creation;
    private LocalDateTime modified;

    public StockLedgerEntry() {}

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getItemCode() { return itemCode; }
    public void setItemCode(String itemCode) { this.itemCode = itemCode; }
    public String getWarehouse() { return warehouse; }
    public void setWarehouse(String warehouse) { this.warehouse = warehouse; }
    public LocalDateTime getPostingDate() { return postingDate; }
    public void setPostingDate(LocalDateTime postingDate) { this.postingDate = postingDate; }
    public LocalDateTime getPostingTime() { return postingTime; }
    public void setPostingTime(LocalDateTime postingTime) { this.postingTime = postingTime; }
    public String getVoucherType() { return voucherType; }
    public void setVoucherType(String voucherType) { this.voucherType = voucherType; }
    public String getVoucherNo() { return voucherNo; }
    public void setVoucherNo(String voucherNo) { this.voucherNo = voucherNo; }
    public String getStockUom() { return stockUom; }
    public void setStockUom(String stockUom) { this.stockUom = stockUom; }
    public BigDecimal getQtyIn() { return qtyIn; }
    public void setQtyIn(BigDecimal qtyIn) { this.qtyIn = qtyIn; }
    public BigDecimal getQtyOut() { return qtyOut; }
    public void setQtyOut(BigDecimal qtyOut) { this.qtyOut = qtyOut; }
    public double getIncomingRate() { return incomingRate; }
    public void setIncomingRate(double incomingRate) { this.incomingRate = incomingRate; }
    public double getOutgoingRate() { return outgoingRate; }
    public void setOutgoingRate(double outgoingRate) { this.outgoingRate = outgoingRate; }
    public double getValuationRate() { return valuationRate; }
    public void setValuationRate(double valuationRate) { this.valuationRate = valuationRate; }
    public String getCompany() { return company; }
    public void setCompany(String company) { this.company = company; }
    public String getFiscalYear() { return fiscalYear; }
    public void setFiscalYear(String fiscalYear) { this.fiscalYear = fiscalYear; }
    public boolean isCancelled() { return isCancelled; }
    public void setIsCancelled(boolean cancelled) { isCancelled = cancelled; }
    public LocalDateTime getCreation() { return creation; }
    public void setCreation(LocalDateTime creation) { this.creation = creation; }
    public LocalDateTime getModified() { return modified; }
    public void setModified(LocalDateTime modified) { this.modified = modified; }
}