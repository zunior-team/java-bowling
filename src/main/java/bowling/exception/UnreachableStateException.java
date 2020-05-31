package bowling.exception;

public class UnreachableStateException extends RuntimeException {
    public UnreachableStateException() {
        super("You can't play like this way");
    }
}
