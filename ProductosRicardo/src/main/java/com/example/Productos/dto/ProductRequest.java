package com.example.Productos.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record ProductRequest(
        @NotBlank(message = "name is mandatory")
        @NotNull(message = "name can not be empty")
        @Valid
        String name,
        @Positive
        Double price,
        @PositiveOrZero
        Integer stock,
        @NotNull
        Long categoryId
) {

}
