package com.uzimahealth.stock;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "product_bundle_item")
@Data
public class ProductBundleItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @Column(nullable = false)
    private Double quantity;

    @Column(length = 100)
    private String uom;

    @Column
    private Double rate;

    @Column
    private Double amount;
}