package entities.exceptions;

public class InputDomainException extends RuntimeException {
    public InputDomainException(String msg) {
        super(msg);
    }
}