package bowling.frame;

import bowling.exception.UnReachableStateException;
import bowling.pin.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("프레임 집합에 대한 테스트")
class FramesTest {

    @Test
    @DisplayName("초기화")
    void init() {
        assertThatCode(Frames::init).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("볼링공 던지기")
    void downPins() {
        Frames frames = Frames.init();

        for(int i = 0 ; i < 12 ; ++i) {
            frames.downPins(Pin.of(10));
            assertThat(frames.isBowlingEnd()).isFalse();
        }

        frames.downPins(Pin.of(10));
        assertThat(frames.isBowlingEnd()).isTrue();

        assertThatExceptionOfType(UnReachableStateException.class)
                .isThrownBy(() -> frames.downPins(Pin.of(10)));
    }
}