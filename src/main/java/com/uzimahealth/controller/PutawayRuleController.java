package com.uzimahealth.controller;

import com.uzimahealth.stock.PutawayRule;
import com.uzimahealth.repository.PutawayRuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/putaway-rules")
public class PutawayRuleController {

    @Autowired
    private PutawayRuleRepository putawayRuleRepository;

    @GetMapping
    public List<PutawayRule> getAllPutawayRules() {
        return putawayRuleRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PutawayRule> getPutawayRuleById(@PathVariable Long id) {
        return putawayRuleRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public PutawayRule createPutawayRule(@RequestBody PutawayRule putawayRule) {
        return putawayRuleRepository.save(putawayRule);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PutawayRule> updatePutawayRule(@PathVariable Long id, @RequestBody PutawayRule ruleDetails) {
        return putawayRuleRepository.findById(id)
                .map(rule -> {
                    rule.setRuleName(ruleDetails.getRuleName());
                    rule.setItem(ruleDetails.getItem());
                    rule.setItemGroup(ruleDetails.getItemGroup());
                    rule.setWarehouse(ruleDetails.getWarehouse());
                    rule.setPriority(ruleDetails.getPriority());
                    rule.setDisable(ruleDetails.getDisable());
                    rule.setUpdatedAt(java.time.LocalDateTime.now());
                    return ResponseEntity.ok(putawayRuleRepository.save(rule));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePutawayRule(@PathVariable Long id) {
        if (putawayRuleRepository.existsById(id)) {
            putawayRuleRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}