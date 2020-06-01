package bowling.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;

@DisplayName("일반 프레임 테스트")
class NormalFrameTest {

    @Test
    @DisplayName("초기화")
    void init() {
        assertThatCode(() -> NormalFrame.init()).doesNotThrowAnyException();
    }
}