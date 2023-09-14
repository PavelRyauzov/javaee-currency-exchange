package exception.currency;

public class CurrencyAlreadyExistException extends RuntimeException {
    public CurrencyAlreadyExistException(final String message) {
        super(message);
    }
}
