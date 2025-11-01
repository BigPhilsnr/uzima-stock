package com.uzimahealth.controller.transactions;

import com.uzimahealth.stock.DeliveryNote;
import com.uzimahealth.stock.DeliveryNoteItem;
import com.uzimahealth.repository.DeliveryNoteRepository;
import com.uzimahealth.repository.DeliveryNoteItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/delivery")
public class DeliveryController {

    @Autowired
    private DeliveryNoteRepository deliveryNoteRepository;

    @Autowired
    private DeliveryNoteItemRepository deliveryNoteItemRepository;

    // Delivery Note endpoints
    @GetMapping("/notes")
    public ResponseEntity<List<DeliveryNote>> getAllDeliveryNotes() {
        return ResponseEntity.ok(deliveryNoteRepository.findAll());
    }

    @GetMapping("/notes/{id}")
    public ResponseEntity<DeliveryNote> getDeliveryNote(@PathVariable Long id) {
        DeliveryNote note = deliveryNoteRepository.findById(id).orElse(null);
        if (note != null) {
            return ResponseEntity.ok(note);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/notes")
    public ResponseEntity<DeliveryNote> createDeliveryNote(@RequestBody DeliveryNote note) {
        return ResponseEntity.ok(deliveryNoteRepository.save(note));
    }

    @PutMapping("/notes/{id}")
    public ResponseEntity<DeliveryNote> updateDeliveryNote(@PathVariable Long id, @RequestBody DeliveryNote note) {
        DeliveryNote existingNote = deliveryNoteRepository.findById(id).orElse(null);
        if (existingNote != null) {
            note.setId(id);
            return ResponseEntity.ok(deliveryNoteRepository.save(note));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/notes/{id}")
    public ResponseEntity<Void> deleteDeliveryNote(@PathVariable Long id) {
        DeliveryNote note = deliveryNoteRepository.findById(id).orElse(null);
        if (note != null) {
            deliveryNoteRepository.delete(note);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Delivery Note Item endpoints
    @GetMapping("/notes/{noteId}/items")
    public ResponseEntity<List<DeliveryNoteItem>> getDeliveryNoteItems(@PathVariable Long noteId) {
        return ResponseEntity.ok(deliveryNoteItemRepository.findByDeliveryNoteId(noteId));
    }

    @PostMapping("/notes/{noteId}/items")
    public ResponseEntity<DeliveryNoteItem> addDeliveryNoteItem(@PathVariable Long noteId, @RequestBody DeliveryNoteItem item) {
        DeliveryNote note = deliveryNoteRepository.findById(noteId).orElse(null);
        if (note != null) {
            item.setDeliveryNote(note);
            return ResponseEntity.ok(deliveryNoteItemRepository.save(item));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/notes/{noteId}/items/{itemId}")
    public ResponseEntity<DeliveryNoteItem> updateDeliveryNoteItem(@PathVariable Long noteId, @PathVariable Long itemId, @RequestBody DeliveryNoteItem item) {
        DeliveryNoteItem existingItem = deliveryNoteItemRepository.findById(itemId).orElse(null);
        if (existingItem != null && existingItem.getDeliveryNote().getId().equals(noteId)) {
            item.setId(itemId);
            item.setDeliveryNote(existingItem.getDeliveryNote());
            return ResponseEntity.ok(deliveryNoteItemRepository.save(item));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/notes/{noteId}/items/{itemId}")
    public ResponseEntity<Void> deleteDeliveryNoteItem(@PathVariable Long noteId, @PathVariable Long itemId) {
        DeliveryNoteItem item = deliveryNoteItemRepository.findById(itemId).orElse(null);
        if (item != null && item.getDeliveryNote().getId().equals(noteId)) {
            deliveryNoteItemRepository.delete(item);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}