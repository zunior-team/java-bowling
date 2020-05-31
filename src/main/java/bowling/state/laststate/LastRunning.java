package bowling.state.laststate;

import bowling.pin.Pin;
import bowling.state.State;

import java.util.Stack;

public class LastRunning extends State {

    private final Stack<State> states;

    private LastRunning(final State state) {
        this.states = new Stack<>();

        states.add(state);
    }

    public static LastRunning init(final State state) {
        return new LastRunning(state);
    }

    @Override
    public State processDownPins(Pin downPins) {
        return null;
    }
}
