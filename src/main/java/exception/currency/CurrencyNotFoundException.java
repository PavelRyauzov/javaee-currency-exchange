package exception.currency;

public class CurrencyNotFoundException extends RuntimeException {
    public CurrencyNotFoundException(final String message) {
        super(message);
    }
}
