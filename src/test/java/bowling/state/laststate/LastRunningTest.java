package bowling.state.laststate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;

@DisplayName("마지막 프레임에서의 진행중 상태")
class LastRunningTest {

    @Test
    @DisplayName("초기화")
    void init() {
        assertThatCode(LastRunning::init)
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("3회 던졌거나, 마지막 프레임이 miss 인 경우 isEnd() true")
    void isEnd() {
    }
}
