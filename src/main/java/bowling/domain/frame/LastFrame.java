package bowling.domain.frame;

import bowling.domain.state.LastRunning;

import java.util.List;

public class LastFrame extends Frame {

    protected LastFrame() {
        super(LastRunning.init(), LAST_NUM_OF_FRAME);
    }

    public static LastFrame init() {
        return new LastFrame();
    }

    @Override
    public boolean isLastFrameEnd() {
        return isFrameEnd();
    }

    @Override
    public void appendFrame(final List<Frame> frames) {
        // do nothing
    }
}
