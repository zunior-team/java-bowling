package bowling.frame;

import bowling.state.LastRunning;
import bowling.state.State;

import java.util.List;

public class LastFrame extends Frame {

    protected LastFrame(final State state, final int frameNo) {
        super(state, frameNo);
    }

    public static LastFrame init() {
        return new LastFrame(LastRunning.init(), MAXIMUM_OF_FRAME);
    }

    @Override
    public boolean isBowlingEnd() {
        return false;
    }

    @Override
    public void appendFrame(List<Frame> frames) {

    }
}
