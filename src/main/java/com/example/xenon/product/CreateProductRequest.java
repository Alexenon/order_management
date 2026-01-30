package com.example.xenon.product;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
@SuppressWarnings("ClassCanBeRecord")
public class CreateProductRequest {

    private final String name;
    private final String description;
    private final BigDecimal pricePerUnit;

    public CreateProductRequest(String name, String description, BigDecimal pricePerUnit) {
        this.name = name;
        this.description = description;
        this.pricePerUnit = pricePerUnit;
    }

}

