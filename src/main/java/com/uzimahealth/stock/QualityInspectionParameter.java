package com.uzimahealth.stock;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "quality_inspection_parameter")
@Data
public class QualityInspectionParameter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String parameterName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ParameterType parameterType; // NUMERIC, NON_NUMERIC, FORMULA

    @Column
    private Double minValue;

    @Column
    private Double maxValue;

    @Column(length = 500)
    private String acceptanceCriteriaValue;

    @Column(length = 1000)
    private String acceptanceCriteriaFormula;

    @Column
    private Boolean manualInspection = false;

    public enum ParameterType {
        NUMERIC, NON_NUMERIC, FORMULA
    }
}