package bowling.domain.state;

import bowling.domain.pin.Pin;
import bowling.domain.score.Score;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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

        giveExtraChance();
        return this;
    }

    private void giveExtraChance() {
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

    @Override
    public List<State> getState() {
        return new ArrayList<>(states);
    }

    @Override
    public List<Integer> getDownPins() {
        return Collections.emptyList();
    }

    @Override
    protected Score add(Score prevScore) {
        for (State state : states) {
            prevScore = state.addScore(prevScore);
        }

        return prevScore;
    }

    private State getLastState() {
        return states.peek();
    }
}
