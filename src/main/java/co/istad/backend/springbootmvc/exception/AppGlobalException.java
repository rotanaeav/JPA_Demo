package co.istad.backend.springbootmvc.exception;

import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class AppGlobalException {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleValidation(
            MethodArgumentNotValidException e
    ){
        List<FieldErrorResponse> fieldErrorResponses = new ArrayList<>();
        e.getFieldErrors().forEach(fieldError -> {
            fieldErrorResponses.
                    add(new FieldErrorResponse
                            (fieldError.getField(),fieldError.getDefaultMessage()))

        ;});
        return ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .code(HttpStatus.BAD_REQUEST.value())
                .message("Request Data is invalid !")
                .timestamp(Instant.now())
                .errorsDescription(fieldErrorResponses)
                .build();
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<?> handleResponseStatusException(
            ResponseStatusException e
    ) {
        ErrorResponse errorRes = ErrorResponse.builder()
                .status(e.getStatusCode().toString())
                .code(e.getStatusCode().value())
                .message(e.getMessage())
                .timestamp(Instant.now())
                .errorsDescription(e.getReason())
                .build();
        return new ResponseEntity<>
                (errorRes, e.getStatusCode());
    }
}
