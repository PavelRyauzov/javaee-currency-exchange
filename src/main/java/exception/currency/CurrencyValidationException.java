package exception.currency;

import jakarta.validation.ConstraintViolation;

import java.util.Collection;
import java.util.stream.Collectors;

public class CurrencyValidationException extends RuntimeException {
    public CurrencyValidationException(final String message) {
        super(message);
    }

    public CurrencyValidationException(final Collection<ConstraintViolation<Object>> violations) {
        super(violations.stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(", ")));
    }
}
