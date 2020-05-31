package bowling.state;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@DisplayName("스트라이크 테스트")
class StrikeTest {
    private Strike STRIKE;

    @BeforeEach
    void setting() {
        STRIKE = new Strike();
    }

    @Test
    @DisplayName("초기화")
    void init() {
        assertThatCode(Strike::new).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("종료 상태는 true")
    void isEnd() {
        assertThat(STRIKE.isEnd()).isTrue();
    }


}