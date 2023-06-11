package exceptions;

public class KeyIsNotNumberException extends Exception{
    public KeyIsNotNumberException() {
    }

    public KeyIsNotNumberException(String message) {
        super(message);
    }
}
