package bowling.exception;

public class IncalculableException extends RuntimeException {
    public IncalculableException() {
        super("Can't calculate cuz left count is zero");
    }
}
