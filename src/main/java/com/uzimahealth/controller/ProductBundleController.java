package com.uzimahealth.controller;

import com.uzimahealth.stock.ProductBundle;
import com.uzimahealth.repository.ProductBundleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product-bundles")
public class ProductBundleController {

    @Autowired
    private ProductBundleRepository productBundleRepository;

    @GetMapping
    public List<ProductBundle> getAllProductBundles() {
        return productBundleRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductBundle> getProductBundleById(@PathVariable Long id) {
        return productBundleRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ProductBundle createProductBundle(@RequestBody ProductBundle productBundle) {
        return productBundleRepository.save(productBundle);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductBundle> updateProductBundle(@PathVariable Long id, @RequestBody ProductBundle bundleDetails) {
        return productBundleRepository.findById(id)
                .map(bundle -> {
                    bundle.setName(bundleDetails.getName());
                    bundle.setDescription(bundleDetails.getDescription());
                    bundle.setItems(bundleDetails.getItems());
                    bundle.setUpdatedAt(java.time.LocalDateTime.now());
                    return ResponseEntity.ok(productBundleRepository.save(bundle));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductBundle(@PathVariable Long id) {
        if (productBundleRepository.existsById(id)) {
            productBundleRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}