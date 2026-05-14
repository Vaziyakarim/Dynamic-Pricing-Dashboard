package com.pricing.dynamicpricing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.pricing.dynamicpricing.model.Sale;
import com.pricing.dynamicpricing.repository.SaleRepository;

import java.util.List;

@RestController
@RequestMapping("/sales")
@CrossOrigin("*")
public class SaleController {

    @Autowired
    SaleRepository saleRepository;

    @GetMapping("/all")
    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }
}