package co.istad.backend.springbootmvc.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.antlr.v4.runtime.misc.NotNull;

import java.math.BigDecimal;
//1. add validation dependency
//2. define validation rules (constraints @NotBlank, @Size...)
//3. validate rules (@valid at controller)
//4. handle validation errors
public record CreateProductRequest(
        @NotBlank(message = "Name is required")
        @Size(min = 1, max = 100)
        String name,

        @NotNull
        @Positive
        BigDecimal price,

        @NotNull
        @Positive
        Integer qty,

        String description,

        @NotNull
        @Positive
        Integer categoryId
) {
}
