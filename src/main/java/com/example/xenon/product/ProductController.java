package com.example.xenon.product;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/products")
@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return productService.findById(id)
                .map(this::mapped)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<?> createProduct(@RequestBody CreateProductRequest request) {
        Product product = productService.createProduct(request);
        ProductDTO response = mapped(product);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>("Product was successfully deleted", HttpStatus.ACCEPTED);
    }

    private ProductDTO mapped(Product product) {
        return ProductDTO.builder()
                .name(product.getName())
                .description(product.getDescription())
                .pricePerUnit(product.getPricePerUnit())
                .build();
    }

}
