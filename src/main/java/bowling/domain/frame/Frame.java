package bowling.domain.frame;

import bowling.domain.pin.Pin;
import bowling.domain.state.State;

import java.util.List;
import java.util.Objects;

public abstract class Frame {
    protected static final int BASE_NUM_OF_FRAME = 1;
    protected static final int MAXIMUM_OF_FRAME = 10;

    protected State state;
    protected int frameNo;

    protected Frame(final State state, final int frameNo) {
        validate(state);

        this.state = state;
        this.frameNo = frameNo;
    }

    private void validate(final State state) {
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

    public abstract boolean isBowlingEnd();

    public abstract void appendFrame(final List<Frame> frames);
}
