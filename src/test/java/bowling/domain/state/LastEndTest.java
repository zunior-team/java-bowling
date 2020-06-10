package bowling.domain.state;

import bowling.domain.pin.Pin;
import bowling.domain.score.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
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

    @ParameterizedTest
    @MethodSource
    @DisplayName("마지막 프레임 종료 상태는 점수를 구할 수 있다.")
    void getScore(final Stack<State> states, final Score expected) {
        LastEnd lastEnd = LastEnd.init(states);

        assertThat(lastEnd.getScore()).isEqualTo(expected);
    }

    private static Stream<Arguments> getScore() {
        Spare spare = Spare.init(Pin.of(5));
        Miss miss = Miss.init(Pin.of(5), Pin.of(2));
        Stack<State> tripleStrike = new Stack<>();
        tripleStrike.add(Strike.instance());
        tripleStrike.add(Strike.instance());
        tripleStrike.add(Strike.instance());

        Stack<State> strikeMiss = new Stack<>();
        strikeMiss.add(Strike.instance());
        strikeMiss.add(miss);

        Stack<State> strikeSpare = new Stack<>();
        strikeSpare.add(Strike.instance());
        strikeSpare.add(spare);

        Stack<State> spareStrike = new Stack<>();
        spareStrike.add(spare);
        spareStrike.add(Strike.instance());

        Stack<State> spareRunning = new Stack<>();
        spareRunning.add(spare);
        spareRunning.add(Running.init(Pin.of(5)));

        return Stream.of(
                Arguments.of(tripleStrike, Score.of(30)),
                Arguments.of(strikeMiss, Score.of(17)),
                Arguments.of(strikeSpare, Score.of(20)),
                Arguments.of(spareStrike, Score.of(20)),
                Arguments.of(spareRunning, Score.of(15))
        );
    }
}