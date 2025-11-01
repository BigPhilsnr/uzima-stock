package com.uzimahealth.stock;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "stock_reservation")
@Data
public class StockReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String reservationNo;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @Column(nullable = false)
    private String warehouseCode;

    @Column(nullable = false)
    private Double reservedQty;

    @Column
    private String batchNo;

    @Column
    private String serialNo;

    @Column
    private String reservedFor; // Sales Order, etc.

    @Column
    private String referenceNo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.ACTIVE;

    @Column(name = "reservation_date")
    private LocalDateTime reservationDate = LocalDateTime.now();

    @Column(name = "expiry_date")
    private LocalDateTime expiryDate;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

    public enum Status {
        ACTIVE, RELEASED, EXPIRED
    }
}