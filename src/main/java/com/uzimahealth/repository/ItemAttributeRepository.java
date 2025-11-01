package com.uzimahealth.repository;

import com.uzimahealth.stock.ItemAttribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemAttributeRepository extends JpaRepository<ItemAttribute, Long> {
    ItemAttribute findByName(String name);
}