package bowling.domain.state;

import bowling.domain.score.Score;

import java.util.*;

public class LastEnd extends EndState {

    private final Stack<State> states;

    private LastEnd(final Stack<State> states) {
        validate(states);

        this.states = states;
    }

    private void validate(final Stack<State> states) {
        if (Objects.isNull(states) || states.isEmpty()) {
            throw new IllegalArgumentException("States can't be a null or empty");
        }
    }

    public static LastEnd init(final Stack<State> states) {
        return new LastEnd(states);
    }

    @Override
    public List<State> getState() {
        return new ArrayList<>(states);
    }

    @Override
    public List<Integer> getDownPins() {
        return Collections.emptyList();
    }

    @Override
    public Score getScore() {
        List<State> states = new ArrayList<>(this.states);
        State finalState = states.remove(0);

        Score score = finalState.getScore();
        for (State state : states) {
            score = state.addScore(score);
        }

        return score;
    }

    @Override
    protected Score add(Score prevScore) {
        for (State state : states) {
            prevScore = state.addScore(prevScore);
        }

        return prevScore;
    }
}
