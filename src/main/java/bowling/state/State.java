package bowling.state;

import bowling.pin.Pin;

public abstract class State {
    public abstract State downPins(final Pin downPins); // 핀을 쓰러트린 후 상태를 반환

    public boolean isEnd() {
        return false;
    }
}
