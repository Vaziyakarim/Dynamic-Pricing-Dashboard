package com.pricing.dynamicpricing.controller;

import java.util.List;
import java.util.Map;
import java.util.*;
import org.springframework.web.bind.annotation.*;

import com.pricing.dynamicpricing.model.Product;
import com.pricing.dynamicpricing.model.Sale;
import com.pricing.dynamicpricing.service.ProductService;

@RestController
@RequestMapping("/product")
@CrossOrigin("*")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // ADD PRODUCT
    @PostMapping("/add")
    public Product add(@RequestBody Product p) {
        return productService.saveProduct(p);
    }

    // GET ALL PRODUCTS
    @GetMapping("/all")
    public List<Product> all() {
        return productService.getAllProducts();
    }

    // UPDATE PRODUCT
    @PutMapping("/update/{id}")
    public Product update(@PathVariable Long id, @RequestBody Product p) {
        return productService.updateProduct(id, p);
    }

    // RECORD SALE
    @PostMapping("/sale/{id}/{qty}")
    public String sale(@PathVariable Long id, @PathVariable int qty) {
        return productService.recordSale(id, qty);
    }

    // TOTAL REVENUE
    @GetMapping("/revenue")
    public double revenue() {
        return productService.getTotalRevenue();
    }

    // SALES HISTORY
    @GetMapping("/sales/all")
    public List<Sale> getSales() {
        return productService.getAllSales();
    }

    // REVENUE DETAILS FOR CHARTS
    @GetMapping("/revenue-details")
    public Map<String, Object> revenueDetails() {
        return productService.getRevenueDetails();
    }
@DeleteMapping("/delete/{id}")
public String deleteProduct(@PathVariable Long id){
    
    productService.deleteProduct(id);

    return "Product Deleted";
}
}