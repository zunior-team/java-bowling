package bowling.domain.frame;

import bowling.domain.pin.Pin;
import bowling.domain.score.Score;
import bowling.domain.state.Ready;
import bowling.domain.state.State;
import bowling.dto.StateDtos;
import bowling.exception.ParallelNotSupportException;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LastFrame extends Frame {
    private static final int MAX_TRY_COUNT = 3;

    private int tryCount;
    private final Stack<State> states;

    protected LastFrame() {
        super(LAST_NUM_OF_FRAME);

        this.tryCount = 0;
        this.states = new Stack<>();
        this.states.add(Ready.instance());
    }

    public static LastFrame init() {
        return new LastFrame();
    }

    @Override
    public void downPins(final Pin downPins) {
        tryCount++;
        updateLastState(getLastState().downPins(downPins));
        giveExtraChance();
    }

    private void updateLastState(final State updatedState) {
        states.pop();
        states.add(updatedState);
    }

    private void giveExtraChance() {
        if (!isFrameEnd() && getLastState().isCleanState()) { //스페어, 스트라이크면 한번 더 던질 기회를 준다
            states.add(Ready.instance());
        }
    }

    @Override
    public boolean isFrameEnd() {
        return tryCount == MAX_TRY_COUNT || getLastState().isMiss();
    }

    private State getLastState() {
        return states.peek();
    }

    @Override
    public boolean isLastFrameEnd() {
        return isFrameEnd();
    }

    @Override
    public void appendFrame(final List<Frame> frames) {
        // do nothing
    }

    @Override
    public Score addBonusScore(Score score) {
        if (score.isCalculable()) {
            return score;
        }

        return add(score);
    }

    private Score add(Score prevScore) {
        return states.stream()
                .reduce(prevScore,
                        (viaScore, state) -> state.addScore(viaScore),
                        (x, y) -> { throw new ParallelNotSupportException(); });
    }

    @Override
    public Score getScore() {
        List<State> states = new ArrayList<>(this.states);
        State finalState = states.remove(0);

        return states.stream()
                .reduce(finalState.calculateScore(),
                        (viaScore, state) -> state.addScore(viaScore),
                        (x, y) -> { throw new ParallelNotSupportException(); });
    }

    @Override
    public StateDtos getFrameState() {
        return StateDtos.of(new ArrayList<>(states));
    }
}
