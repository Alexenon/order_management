package com.example.xenon.product;

import jakarta.persistence.EntityNotFoundException;
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
        product.setDescription(request.getDescription());
        product.setPricePerUnit(request.getPricePerUnit());
        return repository.save(product);
    }

    public void deleteProduct(Long id) {
        Product product = findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Invalid product id: #" + id));

        repository.delete(product);
    }

}
