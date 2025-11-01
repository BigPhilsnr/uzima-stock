package com.uzimahealth.controller.masters;

import com.uzimahealth.stock.ItemAttribute;
import com.uzimahealth.repository.ItemAttributeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/item-attributes")
public class ItemAttributeController {

    @Autowired
    private ItemAttributeRepository itemAttributeRepository;

    @GetMapping
    public List<ItemAttribute> getAllItemAttributes() {
        return itemAttributeRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemAttribute> getItemAttributeById(@PathVariable Long id) {
        return itemAttributeRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ItemAttribute createItemAttribute(@RequestBody ItemAttribute itemAttribute) {
        return itemAttributeRepository.save(itemAttribute);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemAttribute> updateItemAttribute(@PathVariable Long id, @RequestBody ItemAttribute attributeDetails) {
        return itemAttributeRepository.findById(id)
                .map(attribute -> {
                    attribute.setName(attributeDetails.getName());
                    attribute.setDescription(attributeDetails.getDescription());
                    attribute.setAttributeType(attributeDetails.getAttributeType());
                    attribute.setValues(attributeDetails.getValues());
                    attribute.setNumericMin(attributeDetails.getNumericMin());
                    attribute.setNumericMax(attributeDetails.getNumericMax());
                    return ResponseEntity.ok(itemAttributeRepository.save(attribute));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItemAttribute(@PathVariable Long id) {
        if (itemAttributeRepository.existsById(id)) {
            itemAttributeRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}