package com.uzimahealth.stock;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "shipment")
@Data
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String shipmentNo;

    @Column(nullable = false)
    private String shipmentType; // Outgoing, Incoming

    @Column
    private String pickupFrom;

    @Column
    private String deliverTo;

    @Column
    private String carrier;

    @Column
    private String vehicleNo;

    @Column
    private String driver;

    @Column
    private String driverPhone;

    @Column(length = 500)
    private String remarks;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "shipment_id")
    private List<ShipmentItem> items;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.DRAFT;

    @Column(name = "pickup_date")
    private LocalDateTime pickupDate;

    @Column(name = "delivery_date")
    private LocalDateTime deliveryDate;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

    public enum Status {
        DRAFT, IN_TRANSIT, DELIVERED, CANCELLED
    }
}