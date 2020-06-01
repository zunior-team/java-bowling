package bowling.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("프레임 집합에 대한 테스트")
class FramesTest {

    @Test
    @DisplayName("초기화")
    void init() {
        assertThatCode(() -> Frames.init()).doesNotThrowAnyException();
    }
}