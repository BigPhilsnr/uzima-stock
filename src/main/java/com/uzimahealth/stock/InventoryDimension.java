package com.uzimahealth.stock;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "inventory_dimension")
@Data
public class InventoryDimension {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(length = 500)
    private String description;

    @Column(nullable = false)
    private String dimensionType; // e.g., Location, Project, Cost Center

    @Column
    private Boolean isMandatory = false;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
}