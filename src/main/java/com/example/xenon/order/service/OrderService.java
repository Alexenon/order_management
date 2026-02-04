package com.example.xenon.order.service;

import com.example.xenon.order.Order;
import com.example.xenon.order.OrderItem;
import com.example.xenon.order.OrderStatus;
import com.example.xenon.order.domain.CreateOrderRequest;
import com.example.xenon.order.repository.OrderRepository;
import com.example.xenon.user.User;
import com.example.xenon.user.UserService;
import com.example.xenon.utils.BeanValidator;
import com.example.xenon.utils.exceptions.InternalCriticalException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;
    private final OrderItemService itemService;
    private final BeanValidator validator;

    public OrderService(OrderRepository orderRepository,
                        UserService userService,
                        OrderItemService itemService,
                        BeanValidator validator)
    {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.itemService = itemService;
        this.validator = validator;
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
        return save(order);
    }

    public void deleteProduct(Long orderId) {
        Order order = findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Invalid order id: #" + orderId));

        try {
            orderRepository.delete(order);
        } catch (Exception e) {
            log.error("Cannot delete order #{}", orderId, e);
            throw new InternalCriticalException(e);
        }
    }

    private Order save(Order order) {
        validator.validate(order);
        try {
            return orderRepository.save(order);
        } catch (Exception e) {
            log.error("Cannot save {}", order, e);
            throw new InternalCriticalException(e);
        }
    }


}
