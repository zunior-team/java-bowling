package bowling.domain.state;

import java.util.*;

public class LastEnd extends EndState {

    private final Stack<State> states;

    private LastEnd(final Stack<State> states) {
        validate(states);

        this.states = states;
    }

    private void validate(final Stack<State> states) {
        if (Objects.isNull(states)) {
            throw new IllegalArgumentException("States can't be a null");
        }

        if (states.isEmpty()) {
            throw new IllegalArgumentException("States can't be empty");
        }
    }

    public static LastEnd init(final Stack<State> states) {
        return new LastEnd(states);
    }

    @Override
    public List<State> getState() {
        return new ArrayList<>(states);
    }

    @Override
    public List<Integer> getDownPins() {
        return Collections.emptyList();
    }
}
