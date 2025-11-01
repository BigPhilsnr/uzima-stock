package com.uzimahealth.controller.transactions;

import com.uzimahealth.stock.LandedCostVoucher;
import com.uzimahealth.repository.LandedCostVoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/landed-cost-vouchers")
public class LandedCostVoucherController {

    @Autowired
    private LandedCostVoucherRepository landedCostVoucherRepository;

    @GetMapping
    public List<LandedCostVoucher> getAllLandedCostVouchers() {
        return landedCostVoucherRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LandedCostVoucher> getLandedCostVoucherById(@PathVariable Long id) {
        return landedCostVoucherRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public LandedCostVoucher createLandedCostVoucher(@RequestBody LandedCostVoucher landedCostVoucher) {
        return landedCostVoucherRepository.save(landedCostVoucher);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LandedCostVoucher> updateLandedCostVoucher(@PathVariable Long id, @RequestBody LandedCostVoucher voucherDetails) {
        return landedCostVoucherRepository.findById(id)
                .map(voucher -> {
                    voucher.setVoucherNo(voucherDetails.getVoucherNo());
                    voucher.setPurchaseReceiptNo(voucherDetails.getPurchaseReceiptNo());
                    voucher.setSupplier(voucherDetails.getSupplier());
                    voucher.setTotalLandedCost(voucherDetails.getTotalLandedCost());
                    voucher.setCurrency(voucherDetails.getCurrency());
                    voucher.setItems(voucherDetails.getItems());
                    voucher.setTaxes(voucherDetails.getTaxes());
                    voucher.setStatus(voucherDetails.getStatus());
                    voucher.setPostingDate(voucherDetails.getPostingDate());
                    voucher.setUpdatedAt(java.time.LocalDateTime.now());
                    return ResponseEntity.ok(landedCostVoucherRepository.save(voucher));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLandedCostVoucher(@PathVariable Long id) {
        if (landedCostVoucherRepository.existsById(id)) {
            landedCostVoucherRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}