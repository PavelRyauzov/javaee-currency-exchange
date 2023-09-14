package exception.common;

public class DatabaseException extends RuntimeException {
    public DatabaseException(final String message) {
        super(message);
    }
}
