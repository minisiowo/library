package pl.javastart.library.exceptions;

public class NoSuchOptionException extends RuntimeException {
    public NoSuchOptionException(String message) {
        super(message);
    }
}
