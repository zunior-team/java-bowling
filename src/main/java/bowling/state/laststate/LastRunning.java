package bowling.state.laststate;

import bowling.pin.Pin;
import bowling.state.Ready;
import bowling.state.State;

import java.util.Stack;

public class LastRunning extends State {
    private static final int MAX_TRY_COUNT = 3;

    private int tryCount;
    private final Stack<State> states;

    private LastRunning() {
        this.states = new Stack<>();

        states.add(Ready.instance());
    }

    public static LastRunning init() {
        return new LastRunning();
    }

    @Override
    public State processDownPins(final Pin downPins) {
        tryCount++;

        State updatedState = getLastState().downPins(downPins);
        updateState(updatedState);

        if (isEnd()) {
            return new LastEnd();
        }

        giveExtraChange();
        return this;
    }

    private void giveExtraChange() {
        State lastState = getLastState();

        if (lastState.isCleanState()) {
            states.add(Ready.instance());
        }
    }

    private void updateState(State updatedState) {
        states.pop();
        states.add(updatedState);
    }

    @Override
    public boolean isEnd() {
        return tryCount == MAX_TRY_COUNT || getLastState().isMiss();
    }

    private State getLastState() {
        return states.peek();
    }
}
