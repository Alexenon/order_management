package com.example.xenon.product;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price_per_unit", nullable = false, precision = 12, scale = 2)
    private double pricePerUnit;

}
