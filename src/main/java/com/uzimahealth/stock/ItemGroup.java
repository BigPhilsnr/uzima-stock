package com.uzimahealth.stock;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ItemGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String itemGroupName;
    private String parentItemGroup;
    private boolean isGroup = false;
    private String description;
    private LocalDateTime creation;
    private LocalDateTime modified;

    public ItemGroup() {}

    public ItemGroup(String itemGroupName, String parentItemGroup, boolean isGroup, String description) {
        this.itemGroupName = itemGroupName;
        this.parentItemGroup = parentItemGroup;
        this.isGroup = isGroup;
        this.description = description;
        this.creation = LocalDateTime.now();
        this.modified = LocalDateTime.now();
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getItemGroupName() { return itemGroupName; }
    public void setItemGroupName(String itemGroupName) { this.itemGroupName = itemGroupName; }
    public String getParentItemGroup() { return parentItemGroup; }
    public void setParentItemGroup(String parentItemGroup) { this.parentItemGroup = parentItemGroup; }
    public boolean isGroup() { return isGroup; }
    public void setGroup(boolean group) { isGroup = group; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public LocalDateTime getCreation() { return creation; }
    public void setCreation(LocalDateTime creation) { this.creation = creation; }
    public LocalDateTime getModified() { return modified; }
    public void setModified(LocalDateTime modified) { this.modified = modified; }
}