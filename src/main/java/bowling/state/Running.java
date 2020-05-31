package bowling.state;

import bowling.pin.Pin;

public class Running extends State {

    private final Pin fallenPins;

    private Running(final Pin fallenPins) {
        this.fallenPins = fallenPins;
    }

    public static Running init(final Pin fallenPins) {
        return new Running(fallenPins);
    }

    @Override
    State processDownPins(final Pin downPins) {
        Pin downPinsTotal = fallenPins.add(downPins);

        if (downPinsTotal.isAllDown()) {
            return new Spare();
        }

        return new Miss();
    }
}
