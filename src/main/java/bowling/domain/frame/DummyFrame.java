package bowling.domain.frame;

import bowling.domain.score.Score;
import bowling.domain.state.Ready;

import java.util.List;

// 아무 일도 못하는 더미 프레임
public class DummyFrame extends Frame {
    protected DummyFrame() {
        super(Ready.instance(), -1);
    }

    public static Frame instance() {
        return LazyHolder.DUMMY;
    }

    @Override
    public boolean isLastFrameEnd() {
        return false;
    }

    @Override
    public void appendFrame(List<Frame> frames) {
        // do notrhing
    }

    @Override
    public Score addBonusScore(Score score) {
        return score;
    }

    @Override
    public Score getScore() {
        return null;
    }

    private static class LazyHolder {
        private static final DummyFrame DUMMY = new DummyFrame();

        private LazyHolder() {}
    }
}
