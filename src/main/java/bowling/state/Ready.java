package bowling.state;

import bowling.pin.Pin;

public class Ready extends State {

    private Ready() {}

    public static Ready instance() {
        return LazyHolder.READY;
    }

    @Override
    public State processDownPins(final Pin downPins) {
        if (downPins.isAllDown()) {
            return Strike.instance();
        }

        return Running.init(downPins);
    }

    private static class LazyHolder {
        private static final Ready READY = new Ready();

        private LazyHolder() {}
    }
}
