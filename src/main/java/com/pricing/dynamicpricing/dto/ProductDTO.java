package com.pricing.dynamicpricing.dto;

public class ProductDTO {

    private Long id;
    private String name;
    private double basePrice;
    private double finalPrice;
    private int stock;
    private int demand;

    public ProductDTO(Long id, String name, double basePrice, double finalPrice, int stock, int demand) {
        this.id = id;
        this.name = name;
        this.basePrice = basePrice;
        this.finalPrice = finalPrice;
        this.stock = stock;
        this.demand = demand;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public double getBasePrice() { return basePrice; }
    public double getFinalPrice() { return finalPrice; }
    public int getStock() { return stock; }
    public int getDemand() { return demand; }
}