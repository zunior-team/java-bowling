package bowling.frame;

import bowling.pin.Pin;
import bowling.state.State;

import java.util.Objects;

public abstract class Frame {
    protected State state;

    protected Frame(final State state) {
        validate(state);

        this.state = state;
    }

    private void validate(State state) {
        if (Objects.isNull(state)) {
            throw new IllegalArgumentException("State can't be a null to init frame");
        }
    }

    public void downPins(final Pin downPins) {
        this.state = state.downPins(downPins);
    }

    public boolean isFrameEnd() {
        return state.isEnd();
    }

    public boolean isBowlingEnd() {
        return false;
    }
}
