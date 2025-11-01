package com.uzimahealth.stock;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "packing_slip_item")
@Data
public class PackingSlipItem {
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
    private String packageNo;

    @Column
    private Double netWeight;

    @Column
    private Double grossWeight;
}