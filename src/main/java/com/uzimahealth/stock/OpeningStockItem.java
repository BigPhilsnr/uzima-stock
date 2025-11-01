package com.uzimahealth.stock;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "opening_stock_item")
@Data
public class OpeningStockItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @Column(nullable = false)
    private Double qty;

    @Column
    private Double valuationRate;

    @Column
    private Double amount;

    @Column(length = 100)
    private String uom;

    @Column
    private String batchNo;

    @Column
    private String serialNo;
}