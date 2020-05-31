package bowling.state;

import bowling.pin.Pin;

public class Spare extends EndState {
    private final Pin downPins;

    private Spare(final Pin downPins) {
        this.downPins = downPins;
    }

    public static Spare init(final Pin downPins) {
        return new Spare(downPins);
    }

    @Override
    protected boolean isCleanState() {
        return true;
    }
}
