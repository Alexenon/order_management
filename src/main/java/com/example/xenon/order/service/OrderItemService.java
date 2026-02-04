package com.example.xenon.order.service;

import com.example.xenon.order.OrderItem;
import com.example.xenon.order.domain.CreateOrderItemRequest;
import com.example.xenon.order.repository.OrderItemRepository;
import com.example.xenon.product.Product;
import com.example.xenon.product.ProductService;
import com.example.xenon.utils.BeanValidator;
import com.example.xenon.utils.exceptions.InternalCriticalException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final ProductService productService;
    private final BeanValidator validator;

    public OrderItemService(OrderItemRepository orderItemRepository, ProductService productService, BeanValidator validator) {
        this.orderItemRepository = orderItemRepository;
        this.productService = productService;
        this.validator = validator;
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
        return save(orderItem);
    }

    private OrderItem save(OrderItem orderItem) {
        validator.validate(orderItem);
        try {
            return orderItemRepository.save(orderItem);
        } catch (Exception e) {
            log.error("Cannot save {}", orderItem, e);
            throw new InternalCriticalException(e);
        }
    }


}
