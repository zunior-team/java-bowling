package bowling.state.laststate;

import bowling.pin.Pin;
import bowling.state.State;

public class LastRunning extends State {

    public LastRunning(final Pin downPins) {
    }

    public static LastRunning init(final Pin downPins) {
        return new LastRunning(downPins);
    }

    @Override
    public State processDownPins(Pin downPins) {
        return null;
    }
}
