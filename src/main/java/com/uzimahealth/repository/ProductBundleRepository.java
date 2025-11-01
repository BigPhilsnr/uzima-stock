package com.uzimahealth.repository;

import com.uzimahealth.stock.ProductBundle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductBundleRepository extends JpaRepository<ProductBundle, Long> {
    ProductBundle findByName(String name);
}