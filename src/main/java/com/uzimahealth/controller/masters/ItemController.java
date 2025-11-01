package com.uzimahealth.controller.masters;

import com.uzimahealth.stock.Item;
import com.uzimahealth.stock.ItemGroup;
import com.uzimahealth.stock.UOM;
import com.uzimahealth.repository.ItemRepository;
import com.uzimahealth.repository.ItemGroupRepository;
import com.uzimahealth.repository.UOMRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemGroupRepository itemGroupRepository;

    @Autowired
    private UOMRepository uomRepository;

    // Item endpoints
    @GetMapping
    public ResponseEntity<List<Item>> getAllItems() {
        return ResponseEntity.ok(itemRepository.findAll());
    }

    @GetMapping("/{itemCode}")
    public ResponseEntity<Item> getItemByCode(@PathVariable String itemCode) {
        Item item = itemRepository.findByItemCode(itemCode).orElse(null);
        if (item != null) {
            return ResponseEntity.ok(item);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Item> createItem(@RequestBody Item item) {
        return ResponseEntity.ok(itemRepository.save(item));
    }

    @PutMapping("/{itemCode}")
    public ResponseEntity<Item> updateItem(@PathVariable String itemCode, @RequestBody Item item) {
        Item existingItem = itemRepository.findByItemCode(itemCode).orElse(null);
        if (existingItem != null) {
            item.setItemCode(itemCode);
            return ResponseEntity.ok(itemRepository.save(item));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{itemCode}")
    public ResponseEntity<Void> deleteItem(@PathVariable String itemCode) {
        Item item = itemRepository.findByItemCode(itemCode).orElse(null);
        if (item != null) {
            itemRepository.delete(item);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Item Group endpoints
    @GetMapping("/groups")
    public ResponseEntity<List<ItemGroup>> getAllItemGroups() {
        return ResponseEntity.ok(itemGroupRepository.findAll());
    }

    @PostMapping("/groups")
    public ResponseEntity<ItemGroup> createItemGroup(@RequestBody ItemGroup itemGroup) {
        return ResponseEntity.ok(itemGroupRepository.save(itemGroup));
    }

    // UOM endpoints
    @GetMapping("/uoms")
    public ResponseEntity<List<UOM>> getAllUOMs() {
        return ResponseEntity.ok(uomRepository.findAll());
    }

    @PostMapping("/uoms")
    public ResponseEntity<UOM> createUOM(@RequestBody UOM uom) {
        return ResponseEntity.ok(uomRepository.save(uom));
    }
}