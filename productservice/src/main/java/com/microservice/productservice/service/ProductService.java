package com.microservice.productservice.service;

import com.microservice.productservice.model.ProductRequest;
import com.microservice.productservice.model.ProductResponse;

public interface ProductService {
    long addProduct(ProductRequest productRequest);

    ProductResponse getProduct(long productId);

    void reduceProductQuantityAndUpdate(long productId, long quantity);

    void deleteProduct(long productId);
}
