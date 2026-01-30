package com.example.xenon.order.service;

import com.example.xenon.order.OrderItem;
import com.example.xenon.order.domain.CreateOrderItemRequest;
import com.example.xenon.order.repository.OrderItemRepository;
import com.example.xenon.product.Product;
import com.example.xenon.product.ProductService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final ProductService productService;

    public OrderItemService(OrderItemRepository orderItemRepository, ProductService productService) {
        this.orderItemRepository = orderItemRepository;
        this.productService = productService;
    }

    public Optional<OrderItem> findById(Long id) {
        return orderItemRepository.findById(id);
    }

    public OrderItem createOrderItem(CreateOrderItemRequest request) {
        Product product = productService.findById(request.getProductId())
                .orElseThrow(() -> new EntityNotFoundException("Invalid portfolio id: #" + request.getProductId()));

        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(product);
        orderItem.setQuantity(request.getQuantity());
        orderItem.setTotalPrice(request.getTotalCost());
        return orderItemRepository.save(orderItem);
    }


}
