package bowling.state;

import bowling.pin.Pin;

public class Strike extends State {
    @Override
    public State downPins(Pin downPins) {
        return null;
    }
}
