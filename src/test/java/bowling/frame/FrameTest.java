package bowling.frame;

import bowling.pin.Pin;
import bowling.state.Ready;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@DisplayName("프레임 테스트")
class FrameTest {
    private final Frame frame = new Frame(Ready.instance()) {};

    @Test
    @DisplayName("핀 쓰러뜨리기")
    void downPins() {
        assertThatCode(() -> frame.downPins(Pin.of(10))).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("볼링 끝 기본은 false")
    void isBowlingEnd() {
        assertThat(frame.isBowlingEnd()).isFalse();
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("프레임이 끝났는지 아닌지")
    void isFrameEnd(final Frame frame, final boolean expected) {
        assertThat(frame.isFrameEnd()).isEqualTo(expected);
    }

    private static Stream<Arguments> isFrameEnd() {
        return Stream.of(
                Arguments.of(new Frame(Ready.instance()) {}, false),
                Arguments.of(new Frame(Ready.instance().downPins(Pin.of(10))) {}, true)
        );
    }
}