package bowling.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.util.Stack;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

@DisplayName("마지막 프레임에서의 종료 상태 테스트")
class LastEndTest {

    @Test
    @DisplayName("초기화")
    void init() {
        assertThatCode(() -> LastEnd.init(new Stack<>()))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("초기화 실패")
    void initFail(final Stack<State> states) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> LastEnd.init(states));
    }

    private static Stream<Stack<State>> initFail() {
        return Stream.of(
                null,
                new Stack<>()
        );
    }

    @Test
    @DisplayName("종료 상태는 true")
    void isEnd() {
        assertThat(LastEnd.init(new Stack<>()).isEnd()).isTrue();
    }

}