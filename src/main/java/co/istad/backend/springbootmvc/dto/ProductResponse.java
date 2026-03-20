package co.istad.backend.springbootmvc.dto;

import lombok.Builder;

import java.math.BigDecimal;
@Builder
public record ProductResponse (
     String name,
     String code,
     BigDecimal price,
     Integer qty,
     String description,
     Boolean isAvailable,
     String categoryName

){}
