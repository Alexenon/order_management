package com.example.xenon.order.service;

import com.example.xenon.order.Order;
import com.example.xenon.order.OrderItem;
import com.example.xenon.order.OrderStatus;
import com.example.xenon.order.domain.CreateOrderRequest;
import com.example.xenon.order.repository.OrderRepository;
import com.example.xenon.user.User;
import com.example.xenon.user.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;
    private final OrderItemService itemService;

    public OrderService(OrderRepository orderRepository,
                        UserService userService,
                        OrderItemService itemService) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.itemService = itemService;
    }

    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    public Order placeOrder(CreateOrderRequest request) {
        User user = userService.findById(request.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("Invalid user id: #" + request.getUserId()));

        List<OrderItem> orderItems = request.getItems()
                .stream()
                .map(itemService::createOrderItem)
                .toList();

        Order order = new Order();
        order.setUser(user);
        order.setItems(orderItems);
        order.setStatus(OrderStatus.CREATED);
        return orderRepository.save(order);
    }


}
