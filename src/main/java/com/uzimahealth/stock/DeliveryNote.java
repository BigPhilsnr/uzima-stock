package com.uzimahealth.stock;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class DeliveryNote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String deliveryNoteNo;
    private String customer;
    private LocalDateTime postingDate;
    private LocalDateTime postingTime;
    private String company;
    private boolean isReturn = false;
    private String returnAgainst;
    private String currency;
    private double totalQty;
    private double total;
    private double netTotal;
    private String status;
    @OneToMany(mappedBy = "deliveryNote", cascade = CascadeType.ALL)
    private List<DeliveryNoteItem> items;
    private LocalDateTime creation;
    private LocalDateTime modified;

    public DeliveryNote() {}

    public DeliveryNote(String deliveryNoteNo, String customer, LocalDateTime postingDate,
                        LocalDateTime postingTime, String company, boolean isReturn, String returnAgainst,
                        String currency, double totalQty, double total, double netTotal, String status,
                        List<DeliveryNoteItem> items) {
        this.deliveryNoteNo = deliveryNoteNo;
        this.customer = customer;
        this.postingDate = postingDate;
        this.postingTime = postingTime;
        this.company = company;
        this.isReturn = isReturn;
        this.returnAgainst = returnAgainst;
        this.currency = currency;
        this.totalQty = totalQty;
        this.total = total;
        this.netTotal = netTotal;
        this.status = status;
        this.items = items;
        this.creation = LocalDateTime.now();
        this.modified = LocalDateTime.now();
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getDeliveryNoteNo() { return deliveryNoteNo; }
    public void setDeliveryNoteNo(String deliveryNoteNo) { this.deliveryNoteNo = deliveryNoteNo; }
    public String getCustomer() { return customer; }
    public void setCustomer(String customer) { this.customer = customer; }
    public LocalDateTime getPostingDate() { return postingDate; }
    public void setPostingDate(LocalDateTime postingDate) { this.postingDate = postingDate; }
    public LocalDateTime getPostingTime() { return postingTime; }
    public void setPostingTime(LocalDateTime postingTime) { this.postingTime = postingTime; }
    public String getCompany() { return company; }
    public void setCompany(String company) { this.company = company; }
    public boolean isReturn() { return isReturn; }
    public void setReturn(boolean aReturn) { isReturn = aReturn; }
    public String getReturnAgainst() { return returnAgainst; }
    public void setReturnAgainst(String returnAgainst) { this.returnAgainst = returnAgainst; }
    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }
    public double getTotalQty() { return totalQty; }
    public void setTotalQty(double totalQty) { this.totalQty = totalQty; }
    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
    public double getNetTotal() { return netTotal; }
    public void setNetTotal(double netTotal) { this.netTotal = netTotal; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public List<DeliveryNoteItem> getItems() { return items; }
    public void setItems(List<DeliveryNoteItem> items) { this.items = items; }
    public LocalDateTime getCreation() { return creation; }
    public void setCreation(LocalDateTime creation) { this.creation = creation; }
    public LocalDateTime getModified() { return modified; }
    public void setModified(LocalDateTime modified) { this.modified = modified; }
}