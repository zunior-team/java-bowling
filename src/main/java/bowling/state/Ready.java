package bowling.state;

import bowling.pin.Pin;

public class Ready extends State {
    @Override
    public State downPins(Pin downPins) {
        if (downPins.isAllDown()) {
            return new Strike();
        }

        return new Running();
    }
}
