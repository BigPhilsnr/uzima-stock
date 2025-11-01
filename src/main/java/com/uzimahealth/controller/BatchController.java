package com.uzimahealth.controller;

import com.uzimahealth.stock.Batch;
import com.uzimahealth.stock.SerialNo;
import com.uzimahealth.repository.BatchRepository;
import com.uzimahealth.repository.SerialNoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/batches")
public class BatchController {

    @Autowired
    private BatchRepository batchRepository;

    @Autowired
    private SerialNoRepository serialNoRepository;

    // Batch endpoints
    @GetMapping
    public ResponseEntity<List<Batch>> getAllBatches() {
        return ResponseEntity.ok(batchRepository.findAll());
    }

    @GetMapping("/{batchId}")
    public ResponseEntity<Batch> getBatchById(@PathVariable String batchId) {
        Batch batch = batchRepository.findByBatchId(batchId).orElse(null);
        if (batch != null) {
            return ResponseEntity.ok(batch);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/item/{itemCode}")
    public ResponseEntity<List<Batch>> getBatchesByItem(@PathVariable String itemCode) {
        return ResponseEntity.ok(batchRepository.findByItemCode(itemCode));
    }

    @PostMapping
    public ResponseEntity<Batch> createBatch(@RequestBody Batch batch) {
        return ResponseEntity.ok(batchRepository.save(batch));
    }

    @PutMapping("/{batchId}")
    public ResponseEntity<Batch> updateBatch(@PathVariable String batchId, @RequestBody Batch batch) {
        Batch existingBatch = batchRepository.findByBatchNo(batchId).orElse(null);
        if (existingBatch != null) {
            batch.setBatchId(batchId);
            return ResponseEntity.ok(batchRepository.save(batch));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{batchId}")
    public ResponseEntity<Void> deleteBatch(@PathVariable String batchId) {
        Batch batch = batchRepository.findByBatchNo(batchId).orElse(null);
        if (batch != null) {
            batchRepository.delete(batch);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Serial Number endpoints
    @GetMapping("/serials")
    public ResponseEntity<List<SerialNo>> getAllSerialNumbers() {
        return ResponseEntity.ok(serialNoRepository.findAll());
    }

    @GetMapping("/serials/{serialNo}")
    public ResponseEntity<SerialNo> getSerialNumber(@PathVariable String serialNo) {
        SerialNo serial = serialNoRepository.findBySerialNo(serialNo).orElse(null);
        if (serial != null) {
            return ResponseEntity.ok(serial);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/serials/item/{itemCode}")
    public ResponseEntity<List<SerialNo>> getSerialNumbersByItem(@PathVariable String itemCode) {
        return ResponseEntity.ok(serialNoRepository.findByItemCode(itemCode));
    }

    @PostMapping("/serials")
    public ResponseEntity<SerialNo> createSerialNumber(@RequestBody SerialNo serialNo) {
        return ResponseEntity.ok(serialNoRepository.save(serialNo));
    }

    @PutMapping("/serials/{serialNo}")
    public ResponseEntity<SerialNo> updateSerialNumber(@PathVariable String serialNo, @RequestBody SerialNo serial) {
        SerialNo existingSerial = serialNoRepository.findBySerialNo(serialNo).orElse(null);
        if (existingSerial != null) {
            serial.setSerialNo(serialNo);
            return ResponseEntity.ok(serialNoRepository.save(serial));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/serials/{serialNo}")
    public ResponseEntity<Void> deleteSerialNumber(@PathVariable String serialNo) {
        SerialNo serial = serialNoRepository.findBySerialNo(serialNo).orElse(null);
        if (serial != null) {
            serialNoRepository.delete(serial);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}