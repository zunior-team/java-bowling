package bowling.domain.pin;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// 볼링 핀
public class Pin {
    public static final int MINIMUM_SIZE_OF_PIN = 0;
    public static final int MAXIMUM_SIZE_OF_PIN = 10;

    private final int downPins;

    private Pin(final int downPins) {
        verify(downPins);

        this.downPins = downPins;
    }

    private static void verify(final int fallenPins) {
        if (fallenPins < MINIMUM_SIZE_OF_PIN || MAXIMUM_SIZE_OF_PIN < fallenPins) {
            throw new IllegalArgumentException(
                    "Fallen pin size is invalid value must be range in " +
                            MINIMUM_SIZE_OF_PIN + " ~ " + MAXIMUM_SIZE_OF_PIN
            );
        }
    }

    public static Pin of(final int downPins) {
        verify(downPins);

        return LazyHolder.PINS
                .get(downPins);
    }

    public Pin add(final Pin anotherPin) {
        return of(this.downPins + anotherPin.downPins);
    }

    public boolean isAllDown() {
        return downPins == MAXIMUM_SIZE_OF_PIN;
    }

    public int getNumOfPins() {
        return downPins;
    }

    private static class LazyHolder {
        public static final List<Pin> PINS = IntStream.rangeClosed(MINIMUM_SIZE_OF_PIN, MAXIMUM_SIZE_OF_PIN)
                .boxed()
                .map(Pin::new)
                .collect(Collectors.toList());
    }
}
