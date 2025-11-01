package com.uzimahealth.stock;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "landed_cost_tax")
@Data
public class LandedCostTax {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String taxType;

    @Column(nullable = false)
    private Double taxAmount;

    @Column
    private String description;
}