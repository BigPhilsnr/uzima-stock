package com.uzimahealth.controller.quality;

import com.uzimahealth.stock.QualityInspection;
import com.uzimahealth.repository.QualityInspectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quality-inspections")
public class QualityInspectionController {

    @Autowired
    private QualityInspectionRepository inspectionRepository;

    @GetMapping
    public List<QualityInspection> getAllInspections() {
        return inspectionRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<QualityInspection> getInspectionById(@PathVariable Long id) {
        return inspectionRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/document/{type}/{id}")
    public List<QualityInspection> getInspectionsByDocument(@PathVariable String type, @PathVariable Long id) {
        return inspectionRepository.findByReferenceDocumentTypeAndReferenceDocumentId(type, id);
    }

    @PostMapping
    public QualityInspection createInspection(@RequestBody QualityInspection inspection) {
        return inspectionRepository.save(inspection);
    }

    @PutMapping("/{id}")
    public ResponseEntity<QualityInspection> updateInspection(@PathVariable Long id, @RequestBody QualityInspection inspectionDetails) {
        return inspectionRepository.findById(id)
                .map(inspection -> {
                    inspection.setInspectionType(inspectionDetails.getInspectionType());
                    inspection.setReferenceDocumentType(inspectionDetails.getReferenceDocumentType());
                    inspection.setReferenceDocumentId(inspectionDetails.getReferenceDocumentId());
                    inspection.setItem(inspectionDetails.getItem());
                    inspection.setSampleSize(inspectionDetails.getSampleSize());
                    inspection.setTemplate(inspectionDetails.getTemplate());
                    inspection.setReadings(inspectionDetails.getReadings());
                    inspection.setInspectedBy(inspectionDetails.getInspectedBy());
                    inspection.setVerifiedBy(inspectionDetails.getVerifiedBy());
                    inspection.setRemarks(inspectionDetails.getRemarks());
                    inspection.setStatus(inspectionDetails.getStatus());
                    inspection.setUpdatedAt(java.time.LocalDateTime.now());
                    return ResponseEntity.ok(inspectionRepository.save(inspection));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInspection(@PathVariable Long id) {
        if (inspectionRepository.existsById(id)) {
            inspectionRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}