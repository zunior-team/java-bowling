package bowling.domain.state;

import bowling.domain.pin.Pin;
import bowling.domain.score.Score;

import java.util.Collections;
import java.util.List;

public class Running extends State {
    private final Pin downPins;

    private Running(final Pin downPins) {
        this.downPins = downPins;
    }

    public static Running init(final Pin fallenPins) {
        return new Running(fallenPins);
    }

    @Override
    protected State processDownPins(final Pin downPins) {
        Pin downPinsTotal = this.downPins.add(downPins);

        if (downPinsTotal.isAllDown()) {
            return Spare.init(this.downPins);
        }

        return Miss.init(this.downPins, downPins);
    }

    @Override
    public List<Integer> getDownPins() {
        return Collections.singletonList(downPins.getNumOfPins());
    }

    @Override
    protected Score add(Score prevScore) {
        return prevScore.add(downPins.getNumOfPins());
    }
}
