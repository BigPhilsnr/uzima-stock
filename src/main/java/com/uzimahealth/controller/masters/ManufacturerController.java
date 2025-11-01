package com.uzimahealth.controller.masters;

import com.uzimahealth.stock.Manufacturer;
import com.uzimahealth.repository.ManufacturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/manufacturers")
public class ManufacturerController {

    @Autowired
    private ManufacturerRepository manufacturerRepository;

    @GetMapping
    public List<Manufacturer> getAllManufacturers() {
        return manufacturerRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Manufacturer> getManufacturerById(@PathVariable Long id) {
        return manufacturerRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Manufacturer createManufacturer(@RequestBody Manufacturer manufacturer) {
        return manufacturerRepository.save(manufacturer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Manufacturer> updateManufacturer(@PathVariable Long id, @RequestBody Manufacturer manufacturerDetails) {
        return manufacturerRepository.findById(id)
                .map(manufacturer -> {
                    manufacturer.setName(manufacturerDetails.getName());
                    manufacturer.setDescription(manufacturerDetails.getDescription());
                    manufacturer.setWebsite(manufacturerDetails.getWebsite());
                    manufacturer.setContactPerson(manufacturerDetails.getContactPerson());
                    manufacturer.setPhone(manufacturerDetails.getPhone());
                    manufacturer.setEmail(manufacturerDetails.getEmail());
                    manufacturer.setUpdatedAt(java.time.LocalDateTime.now());
                    return ResponseEntity.ok(manufacturerRepository.save(manufacturer));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteManufacturer(@PathVariable Long id) {
        if (manufacturerRepository.existsById(id)) {
            manufacturerRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}