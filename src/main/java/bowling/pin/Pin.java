package bowling.pin;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// 볼링 핀
public class Pin {
    static final int MINIMUM_SIZE_OF_PIN = 0;
    static final int MAXIMUM_SIZE_OF_PIN = 10;

    private int fallenPins;

    private Pin(final int fallenPins) {
        verify(fallenPins);

        this.fallenPins = fallenPins;
    }

    private static void verify(final int fallenPins) {
        if (fallenPins < MINIMUM_SIZE_OF_PIN || MAXIMUM_SIZE_OF_PIN < fallenPins) {
            throw new IllegalArgumentException(
                    "Fallen pin size is invalid value must be range in " +
                            MINIMUM_SIZE_OF_PIN + " ~ " + MAXIMUM_SIZE_OF_PIN
            );
        }
    }

    public static Pin of(final int fallenPins) {
        verify(fallenPins);

        return LazyHolder.PINS
                .get(fallenPins);
    }

    public Pin add(final Pin anotherPin) {
        return of(this.fallenPins + anotherPin.fallenPins);
    }

    public boolean isAllDown() {
        return fallenPins == MAXIMUM_SIZE_OF_PIN;
    }

    private static class LazyHolder {
        public static final List<Pin> PINS = IntStream.rangeClosed(MINIMUM_SIZE_OF_PIN, MAXIMUM_SIZE_OF_PIN)
                .boxed()
                .map(Pin::new)
                .collect(Collectors.toList());
    }
}
