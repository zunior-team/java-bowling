package bowling.domain.state;

import bowling.domain.pin.Pin;
import bowling.domain.score.Score;
import org.junit.jupiter.api.BeforeAll;
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
    private static LastEnd TRIPLE_STRIKE;
    private static LastEnd STRIKE_MISS;
    private static LastEnd STRIKE_SPARE;
    private static LastEnd SPARE_STRIKE;
    private static LastEnd SPARE_RUNNING;

    @BeforeAll
    private static void setEnv() {
        Spare spare = Spare.init(Pin.of(5));
        Miss miss = Miss.init(Pin.of(5), Pin.of(2));

        Stack<State> tripleStrike = new Stack<>();
        tripleStrike.add(Strike.instance());
        tripleStrike.add(Strike.instance());
        tripleStrike.add(Strike.instance());
        TRIPLE_STRIKE = LastEnd.init(tripleStrike);

        Stack<State> strikeMiss = new Stack<>();
        strikeMiss.add(Strike.instance());
        strikeMiss.add(miss);
        STRIKE_MISS = LastEnd.init(strikeMiss);

        Stack<State> strikeSpare = new Stack<>();
        strikeSpare.add(Strike.instance());
        strikeSpare.add(spare);
        STRIKE_SPARE = LastEnd.init(strikeSpare);

        Stack<State> spareStrike = new Stack<>();
        spareStrike.add(spare);
        spareStrike.add(Strike.instance());
        SPARE_STRIKE = LastEnd.init(spareStrike);

        Stack<State> spareRunning = new Stack<>();
        spareRunning.add(spare);
        spareRunning.add(Running.init(Pin.of(5)));
        SPARE_RUNNING = LastEnd.init(spareRunning);
    }


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
        return Stream.of(
                Arguments.of(TRIPLE_STRIKE, Score.of(30)),
                Arguments.of(STRIKE_MISS, Score.of(17)),
                Arguments.of(STRIKE_SPARE, Score.of(20)),
                Arguments.of(SPARE_STRIKE, Score.of(20)),
                Arguments.of(SPARE_RUNNING, Score.of(15))
        );
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("left 가 1인 경우 가장 첫번째 상태의 addScore 을 호출하여 해당하는 핀의 갯수만큼만 더하고 리턴한다")
    void addScore(final LastEnd lastEnd, final Score expected) {
        assertThat(lastEnd.addScore(Score.ofSpare())).isEqualTo(expected);
    }

    private static Stream<Arguments> addScore() {
        return Stream.of(
                Arguments.of(TRIPLE_STRIKE, Score.of(20, 0)),
                Arguments.of(STRIKE_MISS, Score.of(20, 0)),
                Arguments.of(STRIKE_SPARE, Score.of(20, 0)),
                Arguments.of(SPARE_STRIKE, Score.of(15, 0)),
                Arguments.of(SPARE_RUNNING, Score.of(15, 0))
        );
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("left 가 2인 경우 가장 첫번째 상태의 addScore 을 호출하여 해당하는 핀의 갯수만큼만 더하고 리턴한다")
    void addScoreWithLeft2(final LastEnd lastEnd, final Score expected) {
        assertThat(lastEnd.addScore(Score.ofStrike())).isEqualTo(expected);
    }

    private static Stream<Arguments> addScoreWithLeft2() {
        return Stream.of(
                Arguments.of(TRIPLE_STRIKE, Score.of(30, 0)),
                Arguments.of(STRIKE_MISS, Score.of(25, 0)),
                Arguments.of(STRIKE_SPARE, Score.of(25, 0)),
                Arguments.of(SPARE_STRIKE, Score.of(20, 0)),
                Arguments.of(SPARE_RUNNING, Score.of(20, 0))
        );
    }
}