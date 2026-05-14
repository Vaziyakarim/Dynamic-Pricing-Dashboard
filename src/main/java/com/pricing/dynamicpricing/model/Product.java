package com.pricing.dynamicpricing.model;

import jakarta.persistence.*;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double basePrice;
    private int stock;
    private int demand;
    private double finalPrice;

    // NEW fields for advanced dynamic pricing
    private double marketTrend = 0;
    private int recentSales = 0;
    private boolean peakTime = false;

    public Product() {}

    public Product(String name, double basePrice, int stock, int demand) {
        this.name = name;
        this.basePrice = basePrice;
        this.stock = stock;
        this.demand = demand;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public int getStock() {
        return stock;
    }

    public int getDemand() {
        return demand;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public double getMarketTrend() {
        return marketTrend;
    }

    public int getRecentSales() {
        return recentSales;
    }

    public boolean isPeakTime() {
        return peakTime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setDemand(int demand) {
        this.demand = demand;
    }

    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public void setMarketTrend(double marketTrend) {
        this.marketTrend = marketTrend;
    }

    public void setRecentSales(int recentSales) {
        this.recentSales = recentSales;
    }

    public void setPeakTime(boolean peakTime) {
        this.peakTime = peakTime;
    }
}