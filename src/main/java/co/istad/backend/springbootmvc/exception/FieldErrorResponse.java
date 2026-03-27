package co.istad.backend.springbootmvc.exception;

public record FieldErrorResponse(
     String field,
     String message
){}
