package com.microservice.productservice.service;

import com.microservice.productservice.entity.Product;
import com.microservice.productservice.exception.ProductCustomException;
import com.microservice.productservice.model.ProductRequest;
import com.microservice.productservice.model.ProductResponse;
import com.microservice.productservice.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    public long addProduct(ProductRequest productRequest) {
       log.info("Adding products..");
       Product product=Product.builder().productName(productRequest.getProductName())
               .price(productRequest.getPrice())
               .quantity(productRequest.getQuantity()).build();
        productRepository.save(product);
        log.info("Products added..");
        return product.getProductId();
    }

    @Override
    public ProductResponse getProduct(long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(()-> new ProductCustomException("Product is not available for this ID", "PRODUCT_NOT_FOUND"));
        ProductResponse productResponse = new ProductResponse();
        BeanUtils.copyProperties(product, productResponse);
        return productResponse;
    }

    @Override
    public void reduceProductQuantityAndUpdate(long productId, long quantity) {
        Optional<Product> oldProductDetails = Optional.ofNullable(productRepository
                .findById(productId)
                .orElseThrow(() -> new ProductCustomException("Product is not available for this ID", "PRODUCT_NOT_FOUND")));
        Product updatedDetails = oldProductDetails.get();
        if (updatedDetails.getQuantity() > quantity) {
            updatedDetails.setQuantity(updatedDetails.getQuantity() - quantity);
        } else {
            throw new ProductCustomException("Product Quantity is more than the existing quantity.", "INSUFFICIENT_PRODUCT_QUANTITY");
        }
        productRepository.save(updatedDetails);
        log.info("Prouct details updated and saved successfully..");
        // use response object to return
//        ProductResponse response = ProductResponse.builder()
//                .productId(updatedDetails.getProductId())
//                .productName(updatedDetails.getProductName())
//                .price(updatedDetails.getPrice())
//                .quantity(updatedDetails.getQuantity())
//                .build();
//        return response;
    }

    @Override
    public void deleteProduct(long productId) {
       productRepository.deleteById(productId);
    }
}
