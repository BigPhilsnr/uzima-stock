package com.uzimahealth.stock;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class DeliveryNoteItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "delivery_note_id")
    private DeliveryNote deliveryNote;
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;
    private String description;
    private double qty;
    private String uom;
    private double rate;
    private double amount;
    private String batchNo;
    private LocalDate expiryDate;
    private String serialNo;
    private String warehouse;
    private String againstSalesOrder;
    private String againstSalesInvoice;

    public DeliveryNoteItem() {}

    public DeliveryNoteItem(DeliveryNote deliveryNote, Item item, String description, double qty, String uom,
                            double rate, double amount, String batchNo, LocalDate expiryDate, String serialNo,
                            String warehouse, String againstSalesOrder, String againstSalesInvoice) {
        this.deliveryNote = deliveryNote;
        this.item = item;
        this.description = description;
        this.qty = qty;
        this.uom = uom;
        this.rate = rate;
        this.amount = amount;
        this.batchNo = batchNo;
        this.expiryDate = expiryDate;
        this.serialNo = serialNo;
        this.warehouse = warehouse;
        this.againstSalesOrder = againstSalesOrder;
        this.againstSalesInvoice = againstSalesInvoice;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public DeliveryNote getDeliveryNote() { return deliveryNote; }
    public void setDeliveryNote(DeliveryNote deliveryNote) { this.deliveryNote = deliveryNote; }
    public Item getItem() { return item; }
    public void setItem(Item item) { this.item = item; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public double getQty() { return qty; }
    public void setQty(double qty) { this.qty = qty; }
    public String getUom() { return uom; }
    public void setUom(String uom) { this.uom = uom; }
    public double getRate() { return rate; }
    public void setRate(double rate) { this.rate = rate; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public String getBatchNo() { return batchNo; }
    public void setBatchNo(String batchNo) { this.batchNo = batchNo; }
    public LocalDate getExpiryDate() { return expiryDate; }
    public void setExpiryDate(LocalDate expiryDate) { this.expiryDate = expiryDate; }
    public String getSerialNo() { return serialNo; }
    public void setSerialNo(String serialNo) { this.serialNo = serialNo; }
    public String getWarehouse() { return warehouse; }
    public void setWarehouse(String warehouse) { this.warehouse = warehouse; }
    public String getAgainstSalesOrder() { return againstSalesOrder; }
    public void setAgainstSalesOrder(String againstSalesOrder) { this.againstSalesOrder = againstSalesOrder; }
    public String getAgainstSalesInvoice() { return againstSalesInvoice; }
    public void setAgainstSalesInvoice(String againstSalesInvoice) { this.againstSalesInvoice = againstSalesInvoice; }
}