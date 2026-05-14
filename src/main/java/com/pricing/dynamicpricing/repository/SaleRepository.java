package com.pricing.dynamicpricing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pricing.dynamicpricing.model.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("SELECT SUM(s.totalPrice) FROM Sale s")
    Double getTotalRevenue();
}