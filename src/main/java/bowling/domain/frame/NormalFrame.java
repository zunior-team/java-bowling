package bowling.domain.frame;

import bowling.domain.state.Ready;
import bowling.domain.state.State;

import java.util.List;

public class NormalFrame extends Frame {

    protected NormalFrame(final State state, final int frameNo) {
        super(state, frameNo);
    }

    public static NormalFrame init() {
        return new NormalFrame(Ready.instance(), BASE_NUM_OF_FRAME);
    }

    private Frame next() {
        final int nextFrameNo = frameNo + 1;

        if(nextFrameNo == MAXIMUM_OF_FRAME) {
            return LastFrame.init();
        }

        return new NormalFrame(Ready.instance(), nextFrameNo);
    }

    @Override
    public boolean isBowlingEnd() {
        return false;
    }

    @Override
    public void appendFrame(final List<Frame> frames) {
        if (isFrameEnd()) {
            frames.add(next());
        }
    }

}
