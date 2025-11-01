package com.uzimahealth.controller.masters.itemgroups;

import com.uzimahealth.stock.ItemGroup;
import com.uzimahealth.repository.ItemGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items/groups")
public class ItemGroupController {

    @Autowired
    private ItemGroupRepository itemGroupRepository;

    @GetMapping
    public ResponseEntity<List<ItemGroup>> getAllItemGroups() {
        return ResponseEntity.ok(itemGroupRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<ItemGroup> createItemGroup(@RequestBody ItemGroup itemGroup) {
        return ResponseEntity.ok(itemGroupRepository.save(itemGroup));
    }
}