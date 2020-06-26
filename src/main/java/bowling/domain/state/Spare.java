package bowling.domain.state;

import bowling.domain.pin.Pin;
import bowling.domain.score.Score;

import java.util.Collections;
import java.util.List;

public class Spare extends EndState {
    private final Pin downPins;

    private Spare(final Pin downPins) {
        this.downPins = downPins;
    }

    public static Spare init(final Pin downPins) {
        return new Spare(downPins);
    }

    @Override
    public boolean isCleanState() {
        return true;
    }

    @Override
    public List<Integer> getDownPins() {
        return Collections.singletonList(downPins.getNumOfPins());
    }

    @Override
    public Score calculateScore() {
        return Score.ofSpare();
    }

    @Override
    protected Score add(Score prevScore) {
        prevScore =  prevScore.add(downPins.getNumOfPins());

        if (prevScore.isCalculable()) {
            return prevScore;
        }

        return prevScore.add(Pin.MAXIMUM_SIZE_OF_PIN - downPins.getNumOfPins());
    }
}
