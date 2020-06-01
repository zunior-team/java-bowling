package bowling.frame;

import bowling.state.Ready;
import bowling.state.State;

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

        return new NormalFrame(Ready.instance(), frameNo + 1);
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
