package bowling.exception;

public class ParallelNotSupportException extends RuntimeException {
    public ParallelNotSupportException() {
        super("This reduce stream not support parallel operation");
    }
}
