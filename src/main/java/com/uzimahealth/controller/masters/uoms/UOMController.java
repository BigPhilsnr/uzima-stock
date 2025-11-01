package com.uzimahealth.controller.masters.uoms;

import com.uzimahealth.stock.UOM;
import com.uzimahealth.repository.UOMRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items/uoms")
public class UOMController {

    @Autowired
    private UOMRepository uomRepository;

    @GetMapping
    public ResponseEntity<List<UOM>> getAllUOMs() {
        return ResponseEntity.ok(uomRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<UOM> createUOM(@RequestBody UOM uom) {
        return ResponseEntity.ok(uomRepository.save(uom));
    }
}