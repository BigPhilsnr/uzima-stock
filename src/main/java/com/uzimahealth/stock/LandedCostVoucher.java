package com.uzimahealth.stock;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "landed_cost_voucher")
@Data
public class LandedCostVoucher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String voucherNo;

    @Column
    private String purchaseReceiptNo;

    @Column
    private String supplier;

    @Column(nullable = false)
    private Double totalLandedCost;

    @Column
    private String currency = "KES";

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "landed_cost_voucher_id")
    private List<LandedCostItem> items;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "landed_cost_voucher_id")
    private List<LandedCostTax> taxes;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.DRAFT;

    @Column(name = "posting_date")
    private LocalDateTime postingDate = LocalDateTime.now();

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

    public enum Status {
        DRAFT, SUBMITTED, CANCELLED
    }
}