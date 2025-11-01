package com.uzimahealth.stock;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "item_attribute")
@Data
public class ItemAttribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(length = 500)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AttributeType attributeType; // e.g., Select, Numeric

    @ElementCollection
    @CollectionTable(name = "item_attribute_values", joinColumns = @JoinColumn(name = "item_attribute_id"))
    @Column(name = "value")
    private List<String> values; // for select type

    @Column
    private Double numericMin;

    @Column
    private Double numericMax;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    public enum AttributeType {
        SELECT, NUMERIC
    }
}