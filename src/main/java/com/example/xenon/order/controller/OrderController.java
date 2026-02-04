package com.example.xenon.order.controller;

import com.example.xenon.order.Order;
import com.example.xenon.order.domain.CreateOrderRequest;
import com.example.xenon.order.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            return orderService.findById(id)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            return new ResponseEntity<>("The create operation cannot be done", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> createUser(@RequestBody CreateOrderRequest request) {
        try {
            Order order = orderService.placeOrder(request);
            return new ResponseEntity<>(order, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        try {
            if (orderService.findById(id).isEmpty())
                return ResponseEntity.ok().build();

            orderService.deleteOrder(id);
            return new ResponseEntity<>("Product was successfully deleted", HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>("The delete operation cannot be done", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
