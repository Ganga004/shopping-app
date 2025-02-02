package com.microservices.orderservice.controller;

import com.microservices.orderservice.model.OrderRequest;
import com.microservices.orderservice.service.OrderService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@Log4j2
@RequestMapping("/order")
public class OrderController {

    /*
    * Order Svc  -> Save the data with Order status created
    * Product Svc -> Block products (Reduce the quantity)
    * Payment Svc -> Payment -> Success -> Else Payment failed
    * */

    @Autowired
    private OrderService orderService;

    @PostMapping("/placeOrder")
    public ResponseEntity<Long> placeOrder(@RequestBody OrderRequest orderRequest) {
        long orderId = orderService.placeOrder(orderRequest);
        log.info("order id : {}", Optional.of(orderId));
        return new ResponseEntity<>(orderId, HttpStatus.CREATED);
    }

}
