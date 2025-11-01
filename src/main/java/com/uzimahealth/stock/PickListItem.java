package com.uzimahealth.stock;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "pick_list_item")
@Data
public class PickListItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @Column(nullable = false)
    private Double qty;

    @Column(length = 100)
    private String uom;

    @Column
    private String batchNo;

    @Column
    private String serialNo;

    @Column
    private Double pickedQty = 0.0;

    @Column
    private String location;
}