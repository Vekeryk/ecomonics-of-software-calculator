package cocomo.exception;

public class MissingRequiredFieldException extends RuntimeException {
    
    public MissingRequiredFieldException(String message) {
        super(message);
    }
}
