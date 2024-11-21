package com.microservices.orderservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "Order")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "orderId")
    private long orderId;

    @Column(name = "productId")
    private long productId;

    @Column(name = "quantity")
    private long quantity;

    @Column(name = "orderDate")
    private Instant orderDate;

    @Column(name = "orderStatus")
    private String orderStatus;

    @Column(name = "amount")
    private long amount;
}
