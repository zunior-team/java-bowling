package bowling.state.laststate;

import bowling.pin.Pin;
import bowling.state.Ready;
import bowling.state.State;

import java.util.Stack;

public class LastRunning extends State {

    private final Stack<State> states;

    private LastRunning() {
        this.states = new Stack<>();

        states.add(Ready.instance());
    }

    public static LastRunning init() {
        return new LastRunning();
    }

    @Override
    public State processDownPins(Pin downPins) {
        return null;
    }
}
