package com.uzimahealth.stock;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "opening_stock")
@Data
public class OpeningStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String openingStockNo;

    @Column(nullable = false)
    private String warehouseCode;

    @Column(length = 500)
    private String remarks;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "opening_stock_id")
    private List<OpeningStockItem> items;

    @Column(nullable = false)
    private String company;

    @Column(name = "posting_date")
    private LocalDateTime postingDate = LocalDateTime.now();

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();
}