package com.bej.product.domain;

import org.springframework.data.annotation.Id;

public class Product {
    @Id
    private String productCode;
    private String productName;
    private double price;
    private double discountedPrice;
    private String specifications;
    private boolean stock;
    private int noOfProducts;

    public Product() {
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(double discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    public boolean isStock() {
        return stock;
    }

    public void setStock(boolean stock) {
        this.stock = stock;
    }

    public int getNoOfProducts() {
        return noOfProducts;
    }

    public void setNoOfProducts(int noOfProducts) {
        this.noOfProducts = noOfProducts;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productCode='" + productCode + '\'' +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", discountedPrice=" + discountedPrice +
                ", specifications='" + specifications + '\'' +
                ", stock=" + stock +
                ", noOfProducts=" + noOfProducts +
                '}';
    }
}
