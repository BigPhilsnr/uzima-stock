package com.uzimahealth.repository;

import com.uzimahealth.stock.ItemVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemVariantRepository extends JpaRepository<ItemVariant, Long> {
    List<ItemVariant> findByItemId(Long itemId);
    ItemVariant findByVariantCode(String variantCode);
}