package bowling.exception;

public class UnCalculatableException extends RuntimeException {
    public UnCalculatableException() {
        super("Can't calculate cuz left count is zero");
    }
}
