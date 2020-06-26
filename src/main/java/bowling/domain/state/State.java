package bowling.domain.state;

import bowling.domain.pin.Pin;
import bowling.domain.score.Score;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public abstract class State {

    public State downPins(final Pin downPins) {
        validate(downPins);

        return processDownPins(downPins);
    } // 핀을 쓰러트린 후 상태를 반환

    private void validate(final Pin downPins) {
        if (Objects.isNull(downPins)) {
            throw new IllegalArgumentException("Pin is null");
        }
    }

    protected abstract State processDownPins(final Pin downPins);

    public boolean isEnd() {
        return false;
    }

    public boolean isMiss() {
        return false;
    }

    public boolean isCleanState() {
        return false;
    }

    public List<State> getState() {
        return Collections.singletonList(this);
    }

    public abstract List<Integer> getDownPins();

    public Score calculateScore() {
        return Score.INCALCULABLE;
    }

    public Score addScore(final Score prevScore) {
        if (prevScore.isCalculable()) {
            return prevScore;
        }

        return add(prevScore);
    }

    protected abstract Score add(final Score prevScore);
}
