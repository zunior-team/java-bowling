package bowling.domain.frame;

import bowling.exception.UnReachableStateException;
import bowling.domain.pin.Pin;
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
    @DisplayName("볼링공 던지기, 볼링 종료 테스트")
    void downPins() {
        Frames frames = Frames.init();

        for(int i = 0 ; i < 11 ; ++i) { // 한 게임에서 스트라이크는 최대 12번 던질 수 있다.
            frames.downPins(Pin.of(10));
            assertThat(frames.isBowlingEnd()).isFalse();
        }

        frames.downPins(Pin.of(10));
        assertThat(frames.isBowlingEnd()).isTrue();

        assertThatExceptionOfType(UnReachableStateException.class)
                .isThrownBy(() -> frames.downPins(Pin.of(10)));
    }
}