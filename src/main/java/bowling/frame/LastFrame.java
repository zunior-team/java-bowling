package bowling.frame;

import bowling.state.State;

import java.util.List;

public class LastFrame extends Frame {
    protected LastFrame(final State state, final int frameNo) {
        super(state, frameNo);
    }

    @Override
    public boolean isBowlingEnd() {
        return false;
    }

    @Override
    public void appendFrame(List<Frame> frames) {

    }
}
