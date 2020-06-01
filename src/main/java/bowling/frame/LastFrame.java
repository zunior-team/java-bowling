package bowling.frame;

import bowling.exception.UnReachableStateException;
import bowling.state.LastRunning;

import java.util.List;

public class LastFrame extends Frame {

    protected LastFrame() {
        super(LastRunning.init(), MAXIMUM_OF_FRAME);
    }

    public static LastFrame init() {
        return new LastFrame();
    }

    @Override
    public boolean isBowlingEnd() {
        return isFrameEnd();
    }

    @Override
    public void appendFrame(List<Frame> frames) {
        throw new UnReachableStateException();
    }
}
