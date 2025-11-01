package com.uzimahealth.controller.transactions.stock;

import com.uzimahealth.stock.OpeningStock;
import com.uzimahealth.repository.OpeningStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/opening-stocks")
public class OpeningStockController {

    @Autowired
    private OpeningStockRepository openingStockRepository;

    @GetMapping
    public List<OpeningStock> getAllOpeningStocks() {
        return openingStockRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OpeningStock> getOpeningStockById(@PathVariable Long id) {
        return openingStockRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public OpeningStock createOpeningStock(@RequestBody OpeningStock openingStock) {
        return openingStockRepository.save(openingStock);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OpeningStock> updateOpeningStock(@PathVariable Long id, @RequestBody OpeningStock stockDetails) {
        return openingStockRepository.findById(id)
                .map(stock -> {
                    stock.setOpeningStockNo(stockDetails.getOpeningStockNo());
                    stock.setWarehouseCode(stockDetails.getWarehouseCode());
                    stock.setRemarks(stockDetails.getRemarks());
                    stock.setItems(stockDetails.getItems());
                    stock.setCompany(stockDetails.getCompany());
                    stock.setPostingDate(stockDetails.getPostingDate());
                    stock.setUpdatedAt(java.time.LocalDateTime.now());
                    return ResponseEntity.ok(openingStockRepository.save(stock));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOpeningStock(@PathVariable Long id) {
        if (openingStockRepository.existsById(id)) {
            openingStockRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}