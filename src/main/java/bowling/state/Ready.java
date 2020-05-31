package bowling.state;

import bowling.pin.Pin;

public class Ready extends State {

    public static Ready instance() {
        return LazyHolder.READY;
    }

    @Override
    public State downPins(final Pin downPins) {
        if (downPins.isAllDown()) {
            return new Strike();
        }

        return new Running();
    }

    private static class LazyHolder {
        private static final Ready READY = new Ready();
    }
}
