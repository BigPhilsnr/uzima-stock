package com.uzimahealth.controller;

import com.uzimahealth.stock.QualityInspectionTemplate;
import com.uzimahealth.repository.QualityInspectionTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quality-inspection-templates")
public class QualityInspectionTemplateController {

    @Autowired
    private QualityInspectionTemplateRepository templateRepository;

    @GetMapping
    public List<QualityInspectionTemplate> getAllTemplates() {
        return templateRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<QualityInspectionTemplate> getTemplateById(@PathVariable Long id) {
        return templateRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public QualityInspectionTemplate createTemplate(@RequestBody QualityInspectionTemplate template) {
        return templateRepository.save(template);
    }

    @PutMapping("/{id}")
    public ResponseEntity<QualityInspectionTemplate> updateTemplate(@PathVariable Long id, @RequestBody QualityInspectionTemplate templateDetails) {
        return templateRepository.findById(id)
                .map(template -> {
                    template.setName(templateDetails.getName());
                    template.setDescription(templateDetails.getDescription());
                    template.setParameters(templateDetails.getParameters());
                    template.setUpdatedAt(java.time.LocalDateTime.now());
                    return ResponseEntity.ok(templateRepository.save(template));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTemplate(@PathVariable Long id) {
        if (templateRepository.existsById(id)) {
            templateRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}