package br.com.konisberg.product_api.infra.util;

import br.com.konisberg.product_api.infra.config.exception.ValidationException;
import org.springframework.util.StringUtils;

public class ValidationUtils {

    public static void validateNotEmpty(String field, String fieldName) {
        if (StringUtils.hasText(field)) {
            throw new ValidationException("The " + fieldName + " was not informed.");
        }
    }

    public static void validatePositiveNumber(Number number, String fieldName) {
        if (number == null) {
            throw new ValidationException("The " + fieldName + " was not informed.");
        }
        if (number.doubleValue() <= 0) {
            throw new ValidationException("The " + fieldName + " should not be less or equal to zero.");
        }
    }
}
