package com.example.xenon.order.domain;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class OrderItemDto {

    private final Long orderId;
    private final Long productId;
    private final int quantity;
    private final BigDecimal pricePerUnit;
    private final BigDecimal totalCost;

    @Builder
    public OrderItemDto(Long orderId, Long productId, int quantity, BigDecimal pricePerUnit) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
        this.totalCost = pricePerUnit.multiply(BigDecimal.valueOf(quantity));
    }

}
