package com.uzimahealth.stock;

import jakarta.persistence.*;

@Entity
public class MaterialRequestItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "material_request_id")
    private MaterialRequest materialRequest;
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;
    private String description;
    private double qty;
    private String uom;
    private double orderedQty;
    private double receivedQty;
    private String warehouse;
    private String salesOrder;
    private String project;

    public MaterialRequestItem() {}

    public MaterialRequestItem(MaterialRequest materialRequest, Item item, String description, double qty,
                               String uom, double orderedQty, double receivedQty, String warehouse,
                               String salesOrder, String project) {
        this.materialRequest = materialRequest;
        this.item = item;
        this.description = description;
        this.qty = qty;
        this.uom = uom;
        this.orderedQty = orderedQty;
        this.receivedQty = receivedQty;
        this.warehouse = warehouse;
        this.salesOrder = salesOrder;
        this.project = project;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public MaterialRequest getMaterialRequest() { return materialRequest; }
    public void setMaterialRequest(MaterialRequest materialRequest) { this.materialRequest = materialRequest; }
    public Item getItem() { return item; }
    public void setItem(Item item) { this.item = item; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public double getQty() { return qty; }
    public void setQty(double qty) { this.qty = qty; }
    public String getUom() { return uom; }
    public void setUom(String uom) { this.uom = uom; }
    public double getOrderedQty() { return orderedQty; }
    public void setOrderedQty(double orderedQty) { this.orderedQty = orderedQty; }
    public double getReceivedQty() { return receivedQty; }
    public void setReceivedQty(double receivedQty) { this.receivedQty = receivedQty; }
    public String getWarehouse() { return warehouse; }
    public void setWarehouse(String warehouse) { this.warehouse = warehouse; }
    public String getSalesOrder() { return salesOrder; }
    public void setSalesOrder(String salesOrder) { this.salesOrder = salesOrder; }
    public String getProject() { return project; }
    public void setProject(String project) { this.project = project; }
}