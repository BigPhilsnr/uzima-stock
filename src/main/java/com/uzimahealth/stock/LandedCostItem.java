package com.uzimahealth.stock;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "landed_cost_item")
@Data
public class LandedCostItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @Column(nullable = false)
    private Double qty;

    @Column
    private Double rate;

    @Column
    private Double amount;

    @Column
    private Double apportionedCost;
}