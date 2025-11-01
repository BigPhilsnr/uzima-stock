package com.uzimahealth.stock;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "packing_slip")
@Data
public class PackingSlip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String packingSlipNo;

    @Column
    private String deliveryNoteNo;

    @Column
    private String customer;

    @Column(length = 500)
    private String packingDetails;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "packing_slip_id")
    private List<PackingSlipItem> items;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.DRAFT;

    @Column(name = "packed_date")
    private LocalDateTime packedDate;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

    public enum Status {
        DRAFT, PACKED, SHIPPED
    }
}