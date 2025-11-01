package com.uzimahealth.stock;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class UOM {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String uomName;
    private boolean mustBeWholeNumber = false;
    private LocalDateTime creation;
    private LocalDateTime modified;

    public UOM() {}

    public UOM(String uomName, boolean mustBeWholeNumber) {
        this.uomName = uomName;
        this.mustBeWholeNumber = mustBeWholeNumber;
        this.creation = LocalDateTime.now();
        this.modified = LocalDateTime.now();
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUomName() { return uomName; }
    public void setUomName(String uomName) { this.uomName = uomName; }
    public boolean isMustBeWholeNumber() { return mustBeWholeNumber; }
    public void setMustBeWholeNumber(boolean mustBeWholeNumber) { this.mustBeWholeNumber = mustBeWholeNumber; }
    public LocalDateTime getCreation() { return creation; }
    public void setCreation(LocalDateTime creation) { this.creation = creation; }
    public LocalDateTime getModified() { return modified; }
    public void setModified(LocalDateTime modified) { this.modified = modified; }
}