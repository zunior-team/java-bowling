package bowling.state.laststate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;

@DisplayName("마지막 프레임 레디 상태 테스트")
class LastReadyTest {
    private final LastReady LAST_READY = LastReady.instance();

    @Test
    @DisplayName("초기화")
    void init() {
        assertThatCode(LastReady::instance).doesNotThrowAnyException();
    }

}