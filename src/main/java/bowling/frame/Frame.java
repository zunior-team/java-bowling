package bowling.frame;

import bowling.pin.Pin;
import bowling.state.State;

public abstract class Frame {
    protected State state;

    public void downPins(final Pin downPins) {
        this.state = state.downPins(downPins);
    }

    public abstract boolean isFrameEnd();
    public boolean isBowlingEnd() {
        return false;
    }
}
