package bowling.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@DisplayName("준비 상태 테스트")
class ReadyTest {
    private static final Ready READY = new Ready();

    @Test
    @DisplayName("초기화")
    void init() {
        assertThatCode(ReadyTest::new).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("종료 상태는 false")
    void isEnd() {
        assertThat(READY.isEnd()).isFalse();
    }
}