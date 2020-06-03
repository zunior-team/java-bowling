package bowling.domain.state;

import bowling.domain.pin.Pin;

import java.util.Collections;
import java.util.List;

public class Strike extends EndState {
    private Strike() {}

    public static Strike instance() {
        return LazyHolder.STRIKE;
    }

    private static class LazyHolder {
        public static final Strike STRIKE = new Strike();

        private LazyHolder() {}
    }

    @Override
    protected boolean isCleanState() {
        return true;
    }

    @Override
    public List<Integer> getDownPins() {
        return Collections.singletonList(Pin.MAXIMUM_SIZE_OF_PIN);
    }
}
