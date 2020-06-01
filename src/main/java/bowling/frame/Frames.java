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
        Frame curFrame = getCurFrame();
        
        curFrame.downPins(downPins);
        curFrame.appendFrame(frames);
    }

    public boolean isBowlingEnd() {
        return getCurFrame().isBowlingEnd();
    }

    private Frame getCurFrame() {
        return frames.get(frames.size() - 1);
    }
}
