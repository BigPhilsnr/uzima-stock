package com.uzimahealth.repository;

import com.uzimahealth.stock.ItemGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemGroupRepository extends JpaRepository<ItemGroup, Long> {
}