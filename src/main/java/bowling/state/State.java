package bowling.state;

import bowling.pin.Pin;

import java.util.Objects;

public abstract class State {

    public State downPins(final Pin downPins) {
        validate(downPins);

        return processDownPins(downPins);
    } // 핀을 쓰러트린 후 상태를 반환

    private void validate(final Pin downPins) {
        if (Objects.isNull(downPins)) {
            throw new IllegalArgumentException("Pin is null");
        }
    }

    public abstract State processDownPins(final Pin downPins);

    public boolean isEnd() {
        return false;
    }
}
