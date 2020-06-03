package bowling.domain.score;

import java.util.ArrayList;
import java.util.List;

public class Score {
    private static final Score NOT_CALCULATED_YET = new Score(-1);

    private final int size;
    private final List<Integer> score;

    private Score(final int size) {
        this.size = size;
        score = new ArrayList<>();
    }

    public static Score init() {
        return new Score(2);
    }

    public static Score ofSpare() {
        return new Score(3);
    }

    public static Score ofStrike() {
        return new Score(3);
    }

    private boolean isCalculatable() {
        return score.size() == size;
    }

}
