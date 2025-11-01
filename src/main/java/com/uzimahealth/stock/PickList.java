package com.uzimahealth.stock;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pick_list")
@Data
public class PickList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String pickListNo;

    @Column(nullable = false)
    private String warehouseCode;

    @Column
    private String customer;

    @Column(length = 500)
    private String purpose;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "pick_list_id")
    private List<PickListItem> items;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.DRAFT;

    @Column(name = "pick_date")
    private LocalDateTime pickDate;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

    public enum Status {
        DRAFT, PICKED, CANCELLED
    }
}