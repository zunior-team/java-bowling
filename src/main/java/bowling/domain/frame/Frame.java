package bowling.domain.frame;

import bowling.domain.pin.Pin;
import bowling.domain.score.Score;
import bowling.dto.StateDtos;
import bowling.exception.UnReachableStateException;

import java.util.List;

public abstract class Frame {
    public static final int BASE_NUM_OF_FRAME = 1;
    public static final int LAST_NUM_OF_FRAME = 10;

    protected int frameNo;

    protected Frame(final int frameNo) {
        this.frameNo = frameNo;
    }

    public void bowl(final Pin downPins) {
        if (isFrameEnd()) {
            throw new UnReachableStateException();
        }

        downPins(downPins);
    }

    protected abstract void downPins(final Pin downPins);

    public abstract boolean isFrameEnd();

    public boolean isLastFrameEnd() {
        return false;
    }

    public abstract void appendFrame(final List<Frame> frames);

    protected abstract Score addBonusScore(final Score score);

    public abstract Score getScore();

    public abstract StateDtos getFrameState();
}
