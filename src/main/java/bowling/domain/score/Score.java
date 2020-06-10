package bowling.domain.score;

import bowling.exception.IncalculableException;

import java.util.Objects;

public class Score {
    public static final Score INCALCULABLE = new Score(-1, 0);

    private final int score;
    private final int left;

    private Score(final int score, final int left) {
        this.score = score;
        this.left = left;
    }

    public static Score of(final int score, final int left) {
        validateScore(score);
        return new Score(score, left);
    }

    public static Score of(final int score) {
        validateScore(score);
        return new Score(score, 0);
    }

    public static Score ofStrike() {
        return new Score(10, 2);
    }

    public static Score ofSpare() {
        return new Score(10, 1);
    }

    public Score add(final int score) {
        validate(score);

        return new Score(this.score + score, left - 1);
    }

    private void validate(int score) {
        validateScore(score);

        if (left == 0) {
            throw new IncalculableException();
        }
    }

    private static void validateScore(int score) {
        if (score < 0) {
            throw new IllegalArgumentException("Can't add negative value");
        }
    }

    public boolean isCalculable() {
        return this != INCALCULABLE && left == 0;
    }

    public int getScore() {
        if (isCalculable()) {
            return score;
        }

        return INCALCULABLE.getScore();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score1 = (Score) o;
        return score == score1.score &&
                left == score1.left;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score, left);
    }

    @Override
    public String toString() {
        return "Score{" +
                "score=" + score +
                ", left=" + left +
                '}';
    }
}
