package com.microservices.orderservice.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "PRODUCT-SERVICE/product")
public interface ProductService {
    @PutMapping("/update/{id}")
    ResponseEntity<Void> updateProductDetails(@PathVariable("id") long productId, @RequestParam long quantity);
}
