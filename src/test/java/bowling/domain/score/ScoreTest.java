package bowling.domain.score;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;

@DisplayName("점수 테스트")
class ScoreTest {

    @Test
    @DisplayName("초기화")
    void init() {
        assertThatCode(() -> Score.init()).doesNotThrowAnyException();
    }

}