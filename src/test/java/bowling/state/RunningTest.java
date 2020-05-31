package bowling.state;

import bowling.pin.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;

@DisplayName("실행중 상태 테스트")
class RunningTest {

    @Test
    @DisplayName("초기화")
    void init() {
        assertThatCode(() -> Running.init(Pin.of(5))).doesNotThrowAnyException();
    }

}