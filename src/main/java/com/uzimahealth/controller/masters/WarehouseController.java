package com.uzimahealth.controller.masters;

import com.uzimahealth.stock.Warehouse;
import com.uzimahealth.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/warehouses")
public class WarehouseController {

    @Autowired
    private WarehouseRepository warehouseRepository;

    @GetMapping
    public ResponseEntity<List<Warehouse>> getAllWarehouses() {
        return ResponseEntity.ok(warehouseRepository.findAll());
    }

    @GetMapping("/{warehouseName}")
    public ResponseEntity<Warehouse> getWarehouseByName(@PathVariable String warehouseName) {
        Warehouse warehouse = warehouseRepository.findByWarehouseName(warehouseName).orElse(null);
        if (warehouse != null) {
            return ResponseEntity.ok(warehouse);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Warehouse> createWarehouse(@RequestBody Warehouse warehouse) {
        return ResponseEntity.ok(warehouseRepository.save(warehouse));
    }

    @PutMapping("/{warehouseName}")
    public ResponseEntity<Warehouse> updateWarehouse(@PathVariable String warehouseName, @RequestBody Warehouse warehouse) {
        Warehouse existingWarehouse = warehouseRepository.findByWarehouseName(warehouseName).orElse(null);
        if (existingWarehouse != null) {
            warehouse.setWarehouseName(warehouseName);
            return ResponseEntity.ok(warehouseRepository.save(warehouse));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{warehouseName}")
    public ResponseEntity<Void> deleteWarehouse(@PathVariable String warehouseName) {
        Warehouse warehouse = warehouseRepository.findByWarehouseName(warehouseName).orElse(null);
        if (warehouse != null) {
            warehouseRepository.delete(warehouse);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}