package bowling.domain.frame;

import bowling.domain.pin.Pin;
import bowling.domain.score.Score;
import bowling.dto.StateDtos;

import java.util.List;

// 아무 일도 못하는 더미 프레임
public class DummyFrame extends Frame {
    protected DummyFrame() {
        super(-1);
    }

    public static Frame instance() {
        return LazyHolder.DUMMY;
    }

    @Override
    public void downPins(Pin downPins) {
        // do nothing
    }

    @Override
    public boolean isFrameEnd() {
        return false;
    }

    @Override
    public boolean isLastFrameEnd() {
        return false;
    }

    @Override
    public void appendFrame(List<Frame> frames) {
        // do nothing
    }

    @Override
    public Score addBonusScore(Score score) {
        return score;
    }

    @Override
    public Score getScore() {
        return null;
    }

    @Override
    public StateDtos getFrameState() {
        return null;
    }

    private static class LazyHolder {
        private static final DummyFrame DUMMY = new DummyFrame();

        private LazyHolder() {}
    }
}
