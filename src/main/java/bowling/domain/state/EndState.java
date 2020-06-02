package bowling.domain.state;

import bowling.exception.UnReachableStateException;
import bowling.domain.pin.Pin;

public abstract class EndState extends State {

    @Override
    protected State processDownPins(final Pin downPins) {
        throw new UnReachableStateException();
    }

    @Override
    public boolean isEnd() {
        return true;
    }
}
