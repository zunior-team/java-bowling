package bowling.dto;

import bowling.domain.score.Score;

public class ScoreDto {
    private final int score;

    private ScoreDto(final int score) {
        this.score = score;
    }

    public static ScoreDto of(Score score) {
        return new ScoreDto(score.getScore());
    }

    public int getScore() {
        return score;
    }
}
