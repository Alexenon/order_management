package com.example.xenon.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/products")
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            return productService.findById(id)
                    .map(this::mapped)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            return new ResponseEntity<>("The create operation cannot be done", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> createProduct(@RequestBody CreateProductRequest request) {
        try {
            Product product = productService.createProduct(request);
            ProductDTO response = mapped(product);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        try {
            if (productService.findById(id).isEmpty())
                return ResponseEntity.ok().build();

            productService.deleteProduct(id);
            return new ResponseEntity<>("Product was successfully deleted", HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>("The delete operation cannot be done", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private ProductDTO mapped(Product product) {
        return ProductDTO.builder()
                .name(product.getName())
                .description(product.getDescription())
                .pricePerUnit(product.getPricePerUnit())
                .build();
    }


}
