package bowling.domain.frame;

import bowling.domain.pin.Pin;
import bowling.dto.StateDtos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<StateDtos> getTotalStates() {
        return frames.stream()
                .map(Frame::getFrameState)
                .collect(Collectors.toList());
    }

}
