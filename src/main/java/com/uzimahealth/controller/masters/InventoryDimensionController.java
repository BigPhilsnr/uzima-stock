package com.uzimahealth.controller.masters;

import com.uzimahealth.stock.InventoryDimension;
import com.uzimahealth.repository.InventoryDimensionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory-dimensions")
public class InventoryDimensionController {

    @Autowired
    private InventoryDimensionRepository inventoryDimensionRepository;

    @GetMapping
    public List<InventoryDimension> getAllInventoryDimensions() {
        return inventoryDimensionRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventoryDimension> getInventoryDimensionById(@PathVariable Long id) {
        return inventoryDimensionRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public InventoryDimension createInventoryDimension(@RequestBody InventoryDimension inventoryDimension) {
        return inventoryDimensionRepository.save(inventoryDimension);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventoryDimension> updateInventoryDimension(@PathVariable Long id, @RequestBody InventoryDimension dimensionDetails) {
        return inventoryDimensionRepository.findById(id)
                .map(dimension -> {
                    dimension.setName(dimensionDetails.getName());
                    dimension.setDescription(dimensionDetails.getDescription());
                    dimension.setDimensionType(dimensionDetails.getDimensionType());
                    dimension.setIsMandatory(dimensionDetails.getIsMandatory());
                    return ResponseEntity.ok(inventoryDimensionRepository.save(dimension));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventoryDimension(@PathVariable Long id) {
        if (inventoryDimensionRepository.existsById(id)) {
            inventoryDimensionRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}