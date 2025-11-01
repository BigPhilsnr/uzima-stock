package com.uzimahealth.controller;

import com.uzimahealth.stock.PickList;
import com.uzimahealth.repository.PickListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pick-lists")
public class PickListController {

    @Autowired
    private PickListRepository pickListRepository;

    @GetMapping
    public List<PickList> getAllPickLists() {
        return pickListRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PickList> getPickListById(@PathVariable Long id) {
        return pickListRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public PickList createPickList(@RequestBody PickList pickList) {
        return pickListRepository.save(pickList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PickList> updatePickList(@PathVariable Long id, @RequestBody PickList listDetails) {
        return pickListRepository.findById(id)
                .map(list -> {
                    list.setPickListNo(listDetails.getPickListNo());
                    list.setWarehouseCode(listDetails.getWarehouseCode());
                    list.setCustomer(listDetails.getCustomer());
                    list.setPurpose(listDetails.getPurpose());
                    list.setItems(listDetails.getItems());
                    list.setStatus(listDetails.getStatus());
                    list.setPickDate(listDetails.getPickDate());
                    list.setUpdatedAt(java.time.LocalDateTime.now());
                    return ResponseEntity.ok(pickListRepository.save(list));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePickList(@PathVariable Long id) {
        if (pickListRepository.existsById(id)) {
            pickListRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}