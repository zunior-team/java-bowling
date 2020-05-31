package bowling.state;

import java.util.Objects;
import java.util.Stack;

public class LastEnd extends EndState {

    private final Stack<State> states;

    private LastEnd(final Stack<State> states) {
        validate(states);

        this.states = states;
    }

    private void validate(Stack<State> states) {
        if (Objects.isNull(states)) {
            throw new IllegalArgumentException("States can't be a null");
        }
    }

    public static LastEnd init(final Stack<State> states) {
        return new LastEnd(states);
    }
}
