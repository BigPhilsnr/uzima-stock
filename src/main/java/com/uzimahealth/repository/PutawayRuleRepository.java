package com.uzimahealth.repository;

import com.uzimahealth.stock.PutawayRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PutawayRuleRepository extends JpaRepository<PutawayRule, Long> {
    List<PutawayRule> findByItemId(Long itemId);
    List<PutawayRule> findByItemGroupId(Long itemGroupId);
    List<PutawayRule> findByWarehouse(String warehouse);
}