package bowling.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Stack;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

@DisplayName("마지막 프레임에서의 종료 상태 테스트")
class LastEndTest {

    @Test
    @DisplayName("초기화")
    void init() {
        Stack<State> states = new Stack<>();
        states.add(Ready.instance());

        assertThatCode(() -> LastEnd.init(states))
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
        Stack<State> states = new Stack<>();
        states.add(Ready.instance());

        assertThat(LastEnd.init(states).isEnd()).isTrue();
    }

}