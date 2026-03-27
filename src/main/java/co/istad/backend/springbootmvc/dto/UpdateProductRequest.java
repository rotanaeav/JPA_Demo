package co.istad.backend.springbootmvc.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;


import java.math.BigDecimal;

public record UpdateProductRequest (
        @NotBlank(message = "Name is required")
        @Size(min = 1, max = 100)
        String name,

        @NotNull
        @Positive
        BigDecimal price,

        String description,

        @NotNull
        @Positive
        Integer qty,

        @NotNull
        @Positive
        Integer categoryId)
{
}
