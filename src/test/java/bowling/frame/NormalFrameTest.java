package bowling.frame;

import bowling.pin.Pin;
import bowling.state.Ready;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

// 추상 클래스에 대한 테스트는 어떻게 하는게 좋을까?
@DisplayName("일반 프레임 테스트")
class NormalFrameTest {

    @Test
    @DisplayName("초기화")
    void init() {
        assertThatCode(NormalFrame::init).doesNotThrowAnyException();
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

    @Test
    @DisplayName("핀 쓰러뜨리기")
    void downPins() {
        assertThatCode(() -> NormalFrame.init().downPins(Pin.of(10))).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("볼링 끝 기본은 false")
    void isBowlingEnd() {
        assertThat(NormalFrame.init().isBowlingEnd()).isFalse();
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("프레임이 끝났는지 아닌지")
    void isFrameEnd(final Frame frame, final boolean expected) {
        assertThat(frame.isFrameEnd()).isEqualTo(expected);
    }

    private static Stream<Arguments> isFrameEnd() {
        Frame endFrame = NormalFrame.init();
        endFrame.downPins(Pin.of(10));

        return Stream.of(
                Arguments.of(NormalFrame.init(), false),
                Arguments.of(endFrame, true)
        );
    }

    @Test
    @DisplayName("프레임 번호가 최대 사이즈와 같아진다면 LastFrame 을 생성한다")
    void next() {
        List<Frame> frames = new ArrayList<>();
        NormalFrame endOfNormalFrame = new NormalFrame(Ready.instance(), Frame.MAXIMUM_OF_FRAME - 1);

        endOfNormalFrame.downPins(Pin.of(10));
        endOfNormalFrame.appendFrame(frames);

        Frame lastFrame = frames.get(0);

        assertThat(lastFrame).isInstanceOf(LastFrame.class);
    }
}
