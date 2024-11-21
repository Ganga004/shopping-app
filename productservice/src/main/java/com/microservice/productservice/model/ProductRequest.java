package com.microservice.productservice.model;

import lombok.Data;

@Data
public class ProductRequest {
    private int productId;
    private String productName;
    private long price;
    private long quantity;
}
