package bowling.domain.frame;

import bowling.domain.pin.Pin;
import bowling.domain.score.Score;
import bowling.dto.ScoreDto;
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

    public void bowl(final Pin downPins) {
        Frame curFrame = getCurFrame();
        
        curFrame.bowl(downPins);
        curFrame.appendFrame(frames);
    }

    public boolean isAllFrameEnd() {
        return getCurFrame().isLastFrameEnd();
    }

    private Frame getCurFrame() {
        return frames.get(frames.size() - 1);
    }

    public List<StateDtos> getTotalStates() {
        return frames.stream()
                .map(Frame::getFrameState)
                .collect(Collectors.toList());
    }

    public List<ScoreDto> getScores() {
        return frames.stream()
                .map(Frame::getScore)
                .filter(Score::isCalculable)
                .map(ScoreDto::of)
                .collect(Collectors.toList());
    }
}
