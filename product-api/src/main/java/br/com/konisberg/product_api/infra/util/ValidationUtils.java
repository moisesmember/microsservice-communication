package br.com.konisberg.product_api.infra.util;

import br.com.konisberg.product_api.infra.config.exception.ValidationException;
import org.springframework.util.ObjectUtils;

public class ValidationUtils {

    public static void validateNotEmpty(String field, String fieldName) {
        if (ObjectUtils.isEmpty(field)) {
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

    public static void validateNumberIsGreaterThan(Number firstNumber, Number secondNumber, String fieldName) {
        if (firstNumber == null) {
            throw new ValidationException("The first number was not informed.");
        }
        if (secondNumber == null) {
            throw new ValidationException("The second number was not informed.");
        }
        if (firstNumber.doubleValue() > secondNumber.doubleValue()) {
            throw new ValidationException(fieldName);
        }
    }
}
