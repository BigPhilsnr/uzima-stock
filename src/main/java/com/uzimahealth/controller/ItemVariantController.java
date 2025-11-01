package com.uzimahealth.controller;

import com.uzimahealth.stock.ItemVariant;
import com.uzimahealth.repository.ItemVariantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/item-variants")
public class ItemVariantController {

    @Autowired
    private ItemVariantRepository itemVariantRepository;

    @GetMapping
    public List<ItemVariant> getAllItemVariants() {
        return itemVariantRepository.findAll();
    }

    @GetMapping("/item/{itemId}")
    public List<ItemVariant> getVariantsByItem(@PathVariable Long itemId) {
        return itemVariantRepository.findByItemId(itemId);
    }

    @PostMapping
    public ItemVariant createItemVariant(@RequestBody ItemVariant itemVariant) {
        return itemVariantRepository.save(itemVariant);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemVariant> updateItemVariant(@PathVariable Long id, @RequestBody ItemVariant variantDetails) {
        return itemVariantRepository.findById(id)
                .map(variant -> {
                    variant.setVariantCode(variantDetails.getVariantCode());
                    variant.setAttributes(variantDetails.getAttributes());
                    variant.setPrice(variantDetails.getPrice());
                    return ResponseEntity.ok(itemVariantRepository.save(variant));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItemVariant(@PathVariable Long id) {
        if (itemVariantRepository.existsById(id)) {
            itemVariantRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}