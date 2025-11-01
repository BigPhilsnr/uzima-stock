package com.uzimahealth.repository;

import com.uzimahealth.stock.PickList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PickListRepository extends JpaRepository<PickList, Long> {
    PickList findByPickListNo(String pickListNo);
}