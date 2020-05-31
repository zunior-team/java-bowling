package bowling.state.laststate;

import bowling.pin.Pin;
import bowling.state.Ready;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;

@DisplayName("마지막 프레임에서의 진행중 상태")
class LastRunningTest {

    @Test
    @DisplayName("초기화")
    void init() {
        assertThatCode(() -> LastRunning.init(Ready.instance().downPins(Pin.of(10))))
                .doesNotThrowAnyException();
    }
}
