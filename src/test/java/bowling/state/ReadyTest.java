package bowling.state;

import bowling.pin.Pin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@DisplayName("준비 상태")
@SuppressWarnings("ResultOfMethodCallIgnored")
class ReadyTest {
    private Ready READY;

    @BeforeEach
    void setting() {
        READY = Ready.instance();
    }

    @Test
    @DisplayName("종료 상태는 false")
    void isEnd() {
        assertThat(READY.isEnd()).isFalse();
    }

    @Test
    @DisplayName("10개를 쓰러뜨리면 Strike 를 반환")
    void down10Pins() {
        assertThat(READY.downPins(Pin.of(10))).isInstanceOf(Strike.class);
    }

    @Test
    @DisplayName("10개 미만을 쓰러뜨린경우 Running 반환")
    void downUnder10Pins() {
        assertThat(READY.downPins(Pin.of(9))).isInstanceOf(Running.class);
    }

    @Test
    @DisplayName("싱글톤 적용 instance")
    void instance() {
        assertThatCode(Ready::instance).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("싱글톤이기 때문에 equals 결과가 true")
    void equals() {
        assertThat(Ready.instance()).isEqualTo(Ready.instance());
    }
}
