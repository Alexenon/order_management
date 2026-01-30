package com.example.xenon.order.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class CreateOrderItemRequest {

    private final Long productId;
    private final int quantity;
    private final BigDecimal pricePerUnit;
    private final BigDecimal totalCost;

    @Builder
    public CreateOrderItemRequest(Long productId, int quantity, BigDecimal pricePerUnit) {
        this.productId = productId;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
        this.totalCost = pricePerUnit.multiply(BigDecimal.valueOf(quantity));
    }
}
