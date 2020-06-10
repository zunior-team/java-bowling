package bowling.domain.frame;

import bowling.domain.pin.Pin;
import bowling.domain.score.Score;
import bowling.domain.state.State;
import bowling.dto.StateDtos;

import java.util.List;
import java.util.Objects;

public abstract class Frame {
    public static final int BASE_NUM_OF_FRAME = 1;
    public static final int LAST_NUM_OF_FRAME = 10;

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

    public abstract boolean isLastFrameEnd();

    public abstract void appendFrame(final List<Frame> frames);

    public abstract Score addBonusScore(final Score score);

    public abstract Score getScore();

    public StateDtos getFrameState() {
        return StateDtos.of(state.getState());
    }
}
