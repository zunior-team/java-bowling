package bowling.state;

import bowling.pin.Pin;

public class Miss extends EndState {
    @Override
    State processDownPins(Pin downPins) {
        return null;
    }
}
