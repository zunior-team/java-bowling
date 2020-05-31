package bowling.state.laststate;

import bowling.pin.Pin;
import bowling.state.Running;
import bowling.state.State;

import java.util.Stack;

public class LastRunning extends State {

    private final Stack<State> states;

    private LastRunning(final Pin downPins) {
        this.states = new Stack<>();

        states.add(Running.init(downPins));
    }

    public static LastRunning init(final Pin downPins) {
        return new LastRunning(downPins);
    }

    @Override
    public State processDownPins(Pin downPins) {
        return null;
    }
}
