package com.example.Productos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record ProductRequest(
        Long id,
        @NotBlank(message = "name is mandatory")
        String name,
        @Positive
        Double price,
        @PositiveOrZero
        Integer stock
) {

}
