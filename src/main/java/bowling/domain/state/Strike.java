package bowling.domain.state;

import bowling.domain.pin.Pin;
import bowling.domain.score.Score;

import java.util.Collections;
import java.util.List;

public class Strike extends EndState {
    private Strike() {}

    public static Strike instance() {
        return LazyHolder.STRIKE;
    }

    private static class LazyHolder {
        public static final Strike STRIKE = new Strike();

        private LazyHolder() {}
    }

    @Override
    public boolean isCleanState() {
        return true;
    }

    @Override
    public List<Integer> getDownPins() {
        return Collections.singletonList(Pin.MAXIMUM_SIZE_OF_PIN);
    }

    @Override
    public Score calculateScore() {
        return Score.ofStrike();
    }

    @Override
    protected Score add(final Score prevScore) {
        return prevScore.add(Pin.MAXIMUM_SIZE_OF_PIN);
    }
}
