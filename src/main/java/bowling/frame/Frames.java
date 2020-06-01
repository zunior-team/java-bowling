package bowling.frame;

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
}
