package com.uzimahealth.stock;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "delivery_stop_item")
@Data
public class DeliveryStopItem {
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
    private Double deliveredQty = 0.0;
}