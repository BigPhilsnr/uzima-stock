package com.uzimahealth.stock;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "quality_inspection_reading")
@Data
public class QualityInspectionReading {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "parameter_id", nullable = false)
    private QualityInspectionParameter parameter;

    @Column
    private Double reading1;

    @Column
    private Double reading2;

    @Column
    private Double reading3;

    @Column(length = 500)
    private String readingValue; // for non-numeric

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.PENDING;

    @Column(length = 500)
    private String remarks;

    public enum Status {
        PENDING, ACCEPTED, REJECTED
    }
}