package com.uzimahealth.controller;

import com.uzimahealth.stock.ItemAlternative;
import com.uzimahealth.repository.ItemAlternativeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/item-alternatives")
public class ItemAlternativeController {

    @Autowired
    private ItemAlternativeRepository itemAlternativeRepository;

    @GetMapping
    public List<ItemAlternative> getAllItemAlternatives() {
        return itemAlternativeRepository.findAll();
    }

    @GetMapping("/item/{itemId}")
    public List<ItemAlternative> getAlternativesByItem(@PathVariable Long itemId) {
        return itemAlternativeRepository.findByItemId(itemId);
    }

    @PostMapping
    public ItemAlternative createItemAlternative(@RequestBody ItemAlternative itemAlternative) {
        return itemAlternativeRepository.save(itemAlternative);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItemAlternative(@PathVariable Long id) {
        if (itemAlternativeRepository.existsById(id)) {
            itemAlternativeRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}