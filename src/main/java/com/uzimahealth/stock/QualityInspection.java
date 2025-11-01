package com.uzimahealth.stock;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "quality_inspection")
@Data
public class QualityInspection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private InspectionType inspectionType; // INCOMING, OUTGOING, IN_PROCESS

    @Column(nullable = false)
    private String referenceDocumentType; // Purchase Receipt, Delivery Note, etc.

    @Column
    private Long referenceDocumentId;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @Column
    private Double sampleSize;

    @ManyToOne
    @JoinColumn(name = "template_id")
    private QualityInspectionTemplate template;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "inspection_id")
    private List<QualityInspectionReading> readings;

    @Column
    private String inspectedBy;

    @Column
    private String verifiedBy;

    @Column(length = 1000)
    private String remarks;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.DRAFT;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

    public enum InspectionType {
        INCOMING, OUTGOING, IN_PROCESS
    }

    public enum Status {
        DRAFT, SUBMITTED, ACCEPTED, REJECTED
    }
}