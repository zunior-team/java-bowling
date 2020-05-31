package bowling.state;

import bowling.pin.Pin;

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
}
