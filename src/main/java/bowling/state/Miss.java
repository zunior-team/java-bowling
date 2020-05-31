package bowling.state;

import bowling.pin.Pin;

public class Miss extends EndState {
    private final Pin firstDownPins;
    private final Pin secondDownPins;

    private Miss(final Pin firstDownPins, final Pin secondDownPins) {
        verify(firstDownPins, secondDownPins);

        this.firstDownPins = firstDownPins;
        this.secondDownPins = secondDownPins;
    }

    private void verify(final Pin firstDownPins, final Pin secondDownPins) {
        boolean isAllDown = firstDownPins.add(secondDownPins)
                .isAllDown();

        if (isAllDown) {
            throw new IllegalArgumentException("Miss state should contain not down pins");
        }
    }

    public static Miss init(final Pin firstDownPins, final Pin secondDownPins) {
        return new Miss(firstDownPins, secondDownPins);
    }

    @Override
    public boolean isMiss() {
        return true;
    }
}
