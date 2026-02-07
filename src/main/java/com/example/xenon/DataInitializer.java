package com.example.xenon;

import com.example.xenon.product.CreateProductRequest;
import com.example.xenon.product.ProductService;
import com.example.xenon.user.CreateUserRequest;
import com.example.xenon.user.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserService userService;
    private final ProductService productService;

    public DataInitializer(UserService userService, ProductService productService) {
        this.userService = userService;
        this.productService = productService;
    }

    @Override
    public void run(String... args) {
        /* Fills the database with predefined values */
        fillWithUser();
        fillWithProducts();
    }

    private void fillWithUser() {
        if (userService.getUsersCount() > 0)
            return;

        CreateUserRequest request = CreateUserRequest.builder()
                .username("test")
                .password("test")
                .email("test@test.com")
                .build();

        userService.createUser(request);
    }

    private void fillWithProducts() {
        if (productService.getProductsCount() > 0)
            return;

        CreateProductRequest request = CreateProductRequest.builder()
                .name("Keyboard")
                .description("Basic gaming keyboard")
                .pricePerUnit(BigDecimal.valueOf(30.29))
                .build();

        productService.createProduct(request);
    }


}
