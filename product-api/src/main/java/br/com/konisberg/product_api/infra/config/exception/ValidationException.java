package br.com.konisberg.product_api.infra.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ValidationException extends RuntimeException{
    public ValidationException(String message) {
        super(message);
    }
}
