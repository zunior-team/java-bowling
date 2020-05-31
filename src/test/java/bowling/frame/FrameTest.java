package bowling.frame;

import bowling.pin.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@DisplayName("프레임 테스트")
class FrameTest {
    private final Frame frame = new Frame() {
        @Override
        public boolean isFrameEnd() {
            return false;
        }
    };

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
}