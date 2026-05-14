package com.pricing.dynamicpricing.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pricing.dynamicpricing.service.ProductService;

@RestController
@RequestMapping("/revenue")
public class RevenueController {

    private final ProductService productService;

    public RevenueController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/total")
    public double getTotalRevenue() {
        return productService.getTotalRevenue();
    }
}