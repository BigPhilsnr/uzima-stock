package com.uzimahealth.stock;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "shipment_item")
@Data
public class ShipmentItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @Column(nullable = false)
    private Double qty;

    @Column(length = 100)
    private String uom;

    @Column
    private String batchNo;

    @Column
    private String serialNo;

    @Column
    private String description;
}