package com.uzimahealth.stock;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "delivery_trip")
@Data
public class DeliveryTrip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String tripNo;

    @Column
    private String driver;

    @Column
    private String vehicle;

    @Column
    private String route;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_trip_id")
    private List<DeliveryStop> stops;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.DRAFT;

    @Column(name = "departure_date")
    private LocalDateTime departureDate;

    @Column(name = "return_date")
    private LocalDateTime returnDate;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

    public enum Status {
        DRAFT, IN_PROGRESS, COMPLETED, CANCELLED
    }
}