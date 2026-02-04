package com.example.xenon.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required and cannot contain just white spaces")
    @Size(min = 4, max = 50, message = "Name must be between 4 and 50 characters")
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Size(max = 255, message = "Description is too long")
    @Column(name = "description")
    private String description;

    @NotNull(message = "Price is required")
    @Positive(message = "The price must be positive")
    @Digits(integer = 10, fraction = 2, message = "Price must have up to 10 integer digits and 2 decimal places")
    @Column(name = "price_per_unit", nullable = false, precision = 12, scale = 2)
    private BigDecimal pricePerUnit;

}
