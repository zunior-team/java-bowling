package bowling.exception;

public class UnReachableStateException extends RuntimeException {
    public UnReachableStateException() {
        super("You can't play like this way");
    }
}
