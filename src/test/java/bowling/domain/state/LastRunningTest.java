package bowling.domain.state;

import bowling.domain.pin.Pin;
import bowling.domain.score.Score;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@DisplayName("마지막 프레임에서의 진행중 상태")
class LastRunningTest {
    private static LastRunning END_WITH_MISS;
    private static LastRunning STRIKE;
    private static LastRunning DOUBLE_STRIKE;
    private static LastRunning TRIPLE_STRIKE;
    private static LastRunning STRIKE_MISS;
    private static LastRunning TRY_ONCE;
    private static LastRunning SPARE;

    @BeforeAll
    private static void setEnv() {
        END_WITH_MISS = LastRunning.init();
        END_WITH_MISS.downPins(Pin.of(5));
        END_WITH_MISS.downPins(Pin.of(2));

        STRIKE = LastRunning.init();
        STRIKE.downPins(Pin.of(10));

        DOUBLE_STRIKE = LastRunning.init();
        DOUBLE_STRIKE.downPins(Pin.of(10));
        DOUBLE_STRIKE.downPins(Pin.of(10));

        TRIPLE_STRIKE = LastRunning.init();
        TRIPLE_STRIKE.downPins(Pin.of(10));
        TRIPLE_STRIKE.downPins(Pin.of(10));
        TRIPLE_STRIKE.downPins(Pin.of(10));

        STRIKE_MISS = LastRunning.init();
        STRIKE_MISS.downPins(Pin.of(10));
        STRIKE_MISS.downPins(Pin.of(5));
        STRIKE_MISS.downPins(Pin.of(2));

        TRY_ONCE = LastRunning.init();
        TRY_ONCE.downPins(Pin.of(5));

        SPARE = LastRunning.init();
        SPARE.downPins(Pin.of(5));
        SPARE.downPins(Pin.of(5));
    }

    @Test
    @DisplayName("초기화")
    void init() {
        assertThatCode(LastRunning::init)
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("3회 던졌거나, 마지막 프레임이 miss 인 경우 isEnd() true")
    void isEnd(final LastRunning lastRunning, final boolean expected) {
        assertThat(lastRunning.isEnd()).isEqualTo(expected);
    }

    private static Stream<Arguments> isEnd() {
        return Stream.of(
                Arguments.of(LastRunning.init(), false),
                Arguments.of(END_WITH_MISS, true),
                Arguments.of(STRIKE, false),
                Arguments.of(DOUBLE_STRIKE, false),
                Arguments.of(TRIPLE_STRIKE, true),
                Arguments.of(STRIKE_MISS, true),
                Arguments.of(TRY_ONCE, false),
                Arguments.of(SPARE, false)
        );
    }

    @Test
    @DisplayName("마지막 프레임 진행 상태는 점수를 구할 수 없다")
    void getScore() {
        LastRunning lastRunning = LastRunning.init();

        assertThat(lastRunning.getScore()).isEqualTo(Score.INCALCULABLE);
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("left 가 1인 경우 가장 첫번째 상태의 addScore을 호출하여 해당하는 핀의 갯수만큼만 더하고 리턴한다")
    void addScore(final LastRunning lastRunning, final Score expected) {
        assertThat(lastRunning.addScore(Score.ofSpare())).isEqualTo(expected);
    }

    private static Stream<Arguments> addScore() {
        return Stream.of(
                Arguments.of(LastRunning.init(), Score.ofSpare()),
                Arguments.of(END_WITH_MISS, Score.of(15, 0)),
                Arguments.of(STRIKE, Score.of(20, 0)),
                Arguments.of(DOUBLE_STRIKE, Score.of(20, 0)),
                Arguments.of(TRIPLE_STRIKE, Score.of(20, 0)),
                Arguments.of(STRIKE_MISS, Score.of(20, 0)),
                Arguments.of(TRY_ONCE, Score.of(15, 0)),
                Arguments.of(SPARE, Score.of(15, 0))
        );
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("left 가 2인 경우 가장 첫번째 상태의 addScore을 호출하여 해당하는 핀의 갯수만큼만 더하고 리턴한다")
    void addScoreWithLeft2(final LastRunning lastRunning, final Score expected) {
        assertThat(lastRunning.addScore(Score.ofStrike())).isEqualTo(expected);
    }

    private static Stream<Arguments> addScoreWithLeft2() {
        return Stream.of(
                Arguments.of(LastRunning.init(), Score.ofStrike()),
                Arguments.of(END_WITH_MISS, Score.of(17, 0)),
                Arguments.of(STRIKE, Score.of(20, 1)),
                Arguments.of(DOUBLE_STRIKE, Score.of(30, 0)),
                Arguments.of(TRIPLE_STRIKE, Score.of(30, 0)),
                Arguments.of(STRIKE_MISS, Score.of(25, 0)),
                Arguments.of(TRY_ONCE, Score.of(15, 1)),
                Arguments.of(SPARE, Score.of(20, 0))
        );
    }
}
