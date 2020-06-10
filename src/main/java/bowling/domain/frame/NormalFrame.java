package bowling.domain.frame;

import bowling.domain.score.Score;
import bowling.domain.state.Ready;
import bowling.domain.state.State;

import java.util.List;

public class NormalFrame extends Frame {
    private Frame nextFrame = DummyFrame.instance();

    protected NormalFrame(final State state, final int frameNo) {
        super(state, frameNo);
    }

    public static NormalFrame init() {
        return new NormalFrame(Ready.instance(), BASE_NUM_OF_FRAME);
    }

    private Frame initNextFrame() {
        final int nextFrameNo = frameNo + 1;

        if(nextFrameNo == LAST_NUM_OF_FRAME) {
            return LastFrame.init();
        }

        return new NormalFrame(Ready.instance(), nextFrameNo);
    }

    @Override
    public boolean isLastFrameEnd() {
        return false;
    }

    @Override
    public void appendFrame(final List<Frame> frames) {
        if (isFrameEnd()) {
            return;
        }

        Frame newFrame = initNextFrame();
        frames.add(newFrame);
        newFrame = nextFrame;
    }

    @Override
    public Score addBonusScore(Score score) {
        return null;
    }

}
