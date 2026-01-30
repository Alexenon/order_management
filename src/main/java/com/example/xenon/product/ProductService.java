package com.example.xenon.product;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Optional<Product> findById(Long id) {
        return repository.findById(id);
    }

    public Product createProduct(CreateProductRequest request) {
        Product product = new Product();
        product.setName(request.getName());
        product.setPricePerUnit(request.getPricePerUnit());
        return repository.save(product);
    }

}
