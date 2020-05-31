package bowling.state;

import bowling.exception.UnReachableStateException;
import bowling.pin.Pin;

public abstract class EndState extends State {

    @Override
    protected State processDownPins(Pin downPins) {
        throw new UnReachableStateException();
    }

    @Override
    public boolean isEnd() {
        return true;
    }
}
