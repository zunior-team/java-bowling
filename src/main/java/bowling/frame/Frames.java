package bowling.frame;

import bowling.pin.Pin;

import java.util.ArrayList;
import java.util.List;

public class Frames {
    private final List<Frame> frames;

    private Frames() {
        this.frames = new ArrayList<>();

        frames.add(NormalFrame.init());
    }

    public static Frames init() {
        return new Frames();
    }

    public void downPins(final Pin downPins) {
    }

    public boolean isBowlingEnd() {
        return false;
    }
}
