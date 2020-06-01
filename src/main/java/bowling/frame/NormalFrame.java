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

    @Override
    public boolean isBowlingEnd() {
        return false;
    }

    @Override
    public void appendFrame(final List<Frame> frames) {

    }

}
