package bowling.frame;

import bowling.pin.Pin;
import bowling.state.State;

public abstract class Frame {
    protected State state;

    protected Frame(final State state) {
        this.state = state;
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
