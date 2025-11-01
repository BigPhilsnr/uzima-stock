package com.uzimahealth.stock;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "delivery_stop")
@Data
public class DeliveryStop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String customer;

    @Column
    private String address;

    @Column
    private String contact;

    @Column
    private String phone;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_stop_id")
    private java.util.List<DeliveryStopItem> items;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.PENDING;

    @Column(name = "estimated_arrival")
    private LocalDateTime estimatedArrival;

    @Column(name = "actual_arrival")
    private LocalDateTime actualArrival;

    @Column(name = "departure_time")
    private LocalDateTime departureTime;

    public enum Status {
        PENDING, ARRIVED, COMPLETED, CANCELLED
    }
}