package bowling.state.laststate;

import bowling.pin.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@DisplayName("마지막 프레임 레디 상태 테스트")
class LastReadyTest {
    private final LastReady LAST_READY = LastReady.instance();

    @Test
    @DisplayName("초기화")
    void init() {
        assertThatCode(LastReady::instance).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("equals 테스트")
    void equals() {
        assertThat(LastReady.instance()).isEqualTo(LastReady.instance());
    }

    @Test
    @DisplayName("어떤 결과를 내더라도 다음 상태는 LastRunning")
    void downPins() {
        assertThat(LAST_READY.processDownPins(Pin.of(10))).isInstanceOf(LastRunning.class);
    }
}