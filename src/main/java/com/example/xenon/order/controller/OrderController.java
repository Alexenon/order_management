package com.example.xenon.order.controller;

import com.example.xenon.order.Order;
import com.example.xenon.order.domain.CreateOrderRequest;
import com.example.xenon.order.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(orderService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return orderService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<?> createUser(@RequestBody CreateOrderRequest request, UriComponentsBuilder uriBuilder) {
        Order order = orderService.placeOrder(request);

        return ResponseEntity.created(uriBuilder
                        .replacePath("/orders/add/{orderId}")
                        .build(Map.of("orderId", order.getId())))
                .body(order);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return new ResponseEntity<>("Product was successfully deleted", HttpStatus.ACCEPTED);
    }

}
