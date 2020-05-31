package bowling.state.laststate;

import bowling.pin.Pin;
import bowling.state.Ready;
import bowling.state.State;

public class LastReady extends State {

    private LastReady() {}

    public static LastReady instance() {
        return LazyHolder.LAST_READY;
    }

    @Override
    public State processDownPins(final Pin downPins) {
        return LastRunning.init(Ready.instance().downPins(downPins));
    }

    private static class LazyHolder {
        public static final LastReady LAST_READY = new LastReady();

        private LazyHolder() {}
    }
}
