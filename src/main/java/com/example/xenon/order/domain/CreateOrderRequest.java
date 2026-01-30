package com.example.xenon.order.domain;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
public class CreateOrderRequest {

    private final Long userId;
    private final List<CreateOrderItemRequest> items;
    private final BigDecimal orderTotalCost;

    @Builder
    private CreateOrderRequest(Long userId, List<CreateOrderItemRequest> items) {
        this.userId = userId;
        this.items = List.copyOf(items);
        this.orderTotalCost = this.items.stream()
                .map(CreateOrderItemRequest::getTotalCost)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
