package bowling.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@DisplayName("마지막 프레임에서의 종료 상태 테스트")
class LastEndTest {

    @Test
    @DisplayName("초기화")
    void init() {
        assertThatCode(() -> LastEnd.init(new Stack<>())).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("종료 상태는 true")
    void isEnd() {
        assertThat(LastEnd.init(new Stack<>()).isEnd()).isTrue();
    }
}