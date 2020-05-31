package bowling.state;

import bowling.pin.Pin;

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
        updateLastState(updatedState);

        return decideTotalState();
    }

    private State decideTotalState() {
        if (isEnd()) {
            return LastEnd.init(states);
        }

        giveExtraChange();
        return this;
    }

    private void giveExtraChange() {
        State lastState = getLastState();

        if (lastState.isCleanState()) { //스페어, 스트라이크면 한번 더 던질 기회를 준다
            states.add(Ready.instance());
        }
    }

    private void updateLastState(final State updatedState) {
        states.pop();
        states.add(updatedState);
    }

    @Override
    public boolean isEnd() { // 3회 던졌건, 마지막 상태가 Miss 이면 끝난 거시다
        return tryCount == MAX_TRY_COUNT || getLastState().isMiss();
    }

    private State getLastState() {
        return states.peek();
    }
}
