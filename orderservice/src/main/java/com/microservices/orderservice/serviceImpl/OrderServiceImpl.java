package com.microservices.orderservice.serviceImpl;

import com.microservices.orderservice.entity.Order;
import com.microservices.orderservice.external.ProductService;
import com.microservices.orderservice.model.OrderRequest;
import com.microservices.orderservice.model.Status;
import com.microservices.orderservice.repository.OrderRepository;
import com.microservices.orderservice.service.OrderService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductService productService;

    @Override
    public long placeOrder(OrderRequest orderRequest) {
        productService.updateProductDetails(orderRequest.getProductId(), orderRequest.getQuantity());
        Order order = Order.builder()
                .productId(orderRequest.getProductId())
                .quantity(orderRequest.getQuantity())
                .orderDate(Instant.now())
                .orderStatus(String.valueOf(Status.CREATED))
                .amount(orderRequest.getTotalAmount())
                .build();

        order = orderRepository.save(order);
        log.info("Order created successfully..");
        return order.getOrderId();
    }
}
