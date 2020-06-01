package bowling.frame;

import bowling.pin.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@DisplayName("일반 프레임 테스트")
class NormalFrameTest {

    @Test
    @DisplayName("초기화")
    void init() {
        assertThatCode(() -> NormalFrame.init()).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("다음 프레임 생성")
    void addFrame() {
        List<Frame> frames = new ArrayList<>();
        Frame frame = NormalFrame.init();

        frame.downPins(Pin.of(5));
        frame.appendFrame(frames);
        assertThat(frames).hasSize(0);

        frame.downPins(Pin.of(5));
        frame.appendFrame(frames);
        assertThat(frames).hasSize(1);
    }
}
