package com.pricing.dynamicpricing.service;

import java.util.*;

import org.springframework.stereotype.Service;

import com.pricing.dynamicpricing.model.Product;
import com.pricing.dynamicpricing.model.Sale;
import com.pricing.dynamicpricing.repository.ProductRepository;
import com.pricing.dynamicpricing.repository.SaleRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final SaleRepository saleRepository;

    public ProductService(ProductRepository productRepository, SaleRepository saleRepository) {
        this.productRepository = productRepository;
        this.saleRepository = saleRepository;
    }

    // -----------------------------
    // DYNAMIC PRICING
    // -----------------------------
    private double calculateDynamicPrice(Product p) {

        double price = p.getBasePrice();

        if (p.getDemand() > 70) {
            price *= 1.30;
        } 
        else if (p.getDemand() > 40) {
            price *= 1.15;
        }

        price *= (1 + p.getMarketTrend() / 100);

        if (p.getRecentSales() > 50) {
            price *= 1.20;
        }

        if (p.isPeakTime()) {
            price *= 1.10;
        }

        return Math.round(price * 100.0) / 100.0;
    }

    // -----------------------------
    // ADD PRODUCT
    // -----------------------------
    public Product saveProduct(Product p) {

        p.setFinalPrice(calculateDynamicPrice(p));

        return productRepository.save(p);
    }

    // -----------------------------
    // GET ALL PRODUCTS
    // -----------------------------
    public List<Product> getAllProducts() {

        return productRepository.findAll();
    }

    // -----------------------------
    // UPDATE PRODUCT
    // -----------------------------
    public Product updateProduct(Long id, Product updated) {

        Product p = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        p.setName(updated.getName());
        p.setBasePrice(updated.getBasePrice());
        p.setStock(updated.getStock());
        p.setDemand(updated.getDemand());
        p.setMarketTrend(updated.getMarketTrend());
        p.setRecentSales(updated.getRecentSales());
        p.setPeakTime(updated.isPeakTime());

        p.setFinalPrice(calculateDynamicPrice(p));

        return productRepository.save(p);
    }

    // -----------------------------
    // RECORD SALE
    // -----------------------------
    public String recordSale(Long productId, int quantity) {

        Product p = productRepository.findById(productId).orElse(null);

        if (p == null) {
            return "Product not found";
        }

        if (p.getStock() < quantity) {
            return "Not enough stock";
        }

        // reduce stock
        p.setStock(p.getStock() - quantity);

        // increase demand
        p.setDemand(p.getDemand() + quantity);

        double newPrice = p.getBasePrice() * (1 + (p.getDemand() / 100.0));
        p.setFinalPrice(newPrice);

        productRepository.save(p);

        // save sale history
        Sale s = new Sale();

        s.setProductId(p.getId());
        s.setProductName(p.getName());
        s.setQuantitySold(quantity);

        double revenue = p.getFinalPrice() * quantity;
        s.setTotalPrice(revenue);

        saleRepository.save(s);

        return "Sale recorded successfully";
    }

    // -----------------------------
    // TOTAL REVENUE
    // -----------------------------
    public double getTotalRevenue() {

        Double r = saleRepository.getTotalRevenue();

        return r != null ? r : 0.0;
    }

    // -----------------------------
    // SALES HISTORY
    // -----------------------------
    public List<Sale> getAllSales() {

        return saleRepository.findAll();
    }
// -----------------------------
// REVENUE DETAILS (FOR CHARTS)
// -----------------------------
/// -----------------------------
// REVENUE DETAILS (FOR CHARTS)
// -----------------------------
public Map<String,Object> getRevenueDetails(){

    List<Sale> sales = saleRepository.findAll();

    Map<String,Object> map = new HashMap<>();

    List<String> dailyLabels = new ArrayList<>();
    List<Double> dailyValues = new ArrayList<>();

    List<String> monthlyLabels = new ArrayList<>();
    List<Double> monthlyValues = new ArrayList<>();

    int i = 1;

    for(Sale s : sales){

        dailyLabels.add("Sale " + i);
        dailyValues.add(s.getTotalPrice());

        monthlyLabels.add("Sale " + i);
        monthlyValues.add(s.getTotalPrice());

        i++;
    }

    map.put("dailyLabels", dailyLabels);
    map.put("dailyValues", dailyValues);

    map.put("monthlyLabels", monthlyLabels);
    map.put("monthlyValues", monthlyValues);

    return map;
}
    // -----------------------------
    // DELETE PRODUCT
    // -----------------------------
    public void deleteProduct(Long id){

        Product p = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        productRepository.delete(p);

    }
}