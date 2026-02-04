package com.example.xenon.product;

import com.example.xenon.utils.BeanValidator;
import com.example.xenon.utils.exceptions.InternalCriticalException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final BeanValidator validator;

    public ProductService(ProductRepository productRepository, BeanValidator validator) {
        this.productRepository = productRepository;
        this.validator = validator;
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public long getProductsCount() {
        return productRepository.count();
    }

    public Product createProduct(CreateProductRequest request) {
        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPricePerUnit(request.getPricePerUnit());
        return save(product);
    }

    public void deleteProduct(Long productId) {
        Product product = findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Invalid product id: #" + productId));

        try {
            productRepository.delete(product);
        } catch (Exception e) {
            log.error("Cannot delete product #{}", productId, e);
            throw new InternalCriticalException(e);
        }
    }

    private Product save(Product product) {
        validator.validate(product);
        try {
            return productRepository.save(product);
        } catch (Exception e) {
            log.error("Cannot save {}", product, e);
            throw new InternalCriticalException(e);
        }
    }

}
