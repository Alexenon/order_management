package com.example.xenon.product;

import lombok.Getter;

@Getter
@SuppressWarnings("ClassCanBeRecord")
public class CreateProductRequest {

    private final String name;
    private final double pricePerUnit;

    public CreateProductRequest(String name, double pricePerUnit) {
        this.name = name;
        this.pricePerUnit = pricePerUnit;
    }

}

