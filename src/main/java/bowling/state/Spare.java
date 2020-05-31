package bowling.state;

import bowling.pin.Pin;

public class Spare extends EndState {

    private Spare(final Pin firstDownPins, Pin secondDownPins) {
        verify(firstDownPins, secondDownPins);
    }

    private void verify(final Pin firstDownPins, final Pin secondDownPins) {
        boolean isAllDown = firstDownPins.add(secondDownPins)
                .isAllDown();

        if (!isAllDown) {
            throw new IllegalArgumentException("Spare must down all pins");
        }
    }

    public static Spare init(final Pin firstDownPins, Pin secondDownPins) {
        return null;
    }
}
