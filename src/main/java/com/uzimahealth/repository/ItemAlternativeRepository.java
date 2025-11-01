package com.uzimahealth.repository;

import com.uzimahealth.stock.ItemAlternative;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemAlternativeRepository extends JpaRepository<ItemAlternative, Long> {
    List<ItemAlternative> findByItemId(Long itemId);
    List<ItemAlternative> findByAlternativeItemId(Long alternativeItemId);
}