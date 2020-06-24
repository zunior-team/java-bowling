package bowling.domain.frame;

import bowling.domain.pin.Pin;
import bowling.domain.score.Score;
import bowling.domain.state.Ready;
import bowling.domain.state.State;
import bowling.dto.StateDtos;

import java.util.List;
import java.util.Objects;

public class NormalFrame extends Frame {
    private Frame nextFrame = DummyFrame.instance();
    protected State state;

    protected NormalFrame(final State state, final int frameNo) {
        super(frameNo);
        validate(state);

        this.state = state;
    }

    private void validate(final State state) {
        if (Objects.isNull(state)) {
            throw new IllegalArgumentException("State can't be a null to init frame");
        }
    }

    public static NormalFrame init() {
        return new NormalFrame(Ready.instance(), BASE_NUM_OF_FRAME);
    }

    @Override
    public void downPins(final Pin downPins) {
        this.state = state.downPins(downPins);
    }

    @Override
    public boolean isFrameEnd() {
        return state.isEnd();
    }

    private Frame initNextFrame() {
        final int nextFrameNo = frameNo + 1;

        if(nextFrameNo == LAST_NUM_OF_FRAME) {
            return LastFrame.init();
        }

        return new NormalFrame(Ready.instance(), nextFrameNo);
    }

    @Override
    public void appendFrame(final List<Frame> frames) {
        if (!isFrameEnd()) {
            return;
        }

        Frame newFrame = initNextFrame();
        frames.add(newFrame);
        nextFrame = newFrame;
    }

    @Override
    public Score getScore() {
        Score score = state.calculateScore();
        score = nextFrame.addBonusScore(score);

        return score;
    }

    @Override
    public StateDtos getFrameState() {
        return StateDtos.of(state.getState());
    }

    @Override
    public Score addBonusScore(Score score) {
        score = state.addScore(score);
        score = nextFrame.addBonusScore(score);

        return score;
    }
}
