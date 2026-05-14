package com.pricing.dynamicpricing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pricing.dynamicpricing.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    
}
