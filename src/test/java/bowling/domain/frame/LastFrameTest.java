package bowling.domain.frame;

import bowling.domain.pin.Pin;
import bowling.domain.score.Score;
import bowling.exception.UnReachableStateException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

@DisplayName("마지막 프레임 테스트")
class LastFrameTest {
    private static final Pin TEN = Pin.of(10);
    private static final Pin FIVE = Pin.of(5);
    private static final Pin TWO = Pin.of(2);
    private static LastFrame END_WITH_MISS;
    private static LastFrame STRIKE;
    private static LastFrame SPARE;
    private static LastFrame TRY_ONCE;
    private static LastFrame DOUBLE_STRIKE;
    private static LastFrame TRIPLE_STRIKE;
    private static LastFrame STRIKE_MISS;
    private static LastFrame STRIKE_SPARE;
    private static LastFrame SPARE_STRIKE;
    private static LastFrame SPARE_RUNNING;

    @BeforeAll
    private static void setEnv() {
        END_WITH_MISS = LastFrame.init();
        miss(END_WITH_MISS);

        STRIKE = LastFrame.init();
        strike(STRIKE);

        SPARE = LastFrame.init();
        spare(SPARE);

        TRY_ONCE = LastFrame.init();
        TRY_ONCE.bowl(FIVE);

        DOUBLE_STRIKE = LastFrame.init();
        strike(DOUBLE_STRIKE);
        strike(DOUBLE_STRIKE);

        TRIPLE_STRIKE = LastFrame.init();
        strike(TRIPLE_STRIKE);
        strike(TRIPLE_STRIKE);
        strike(TRIPLE_STRIKE);

        STRIKE_MISS = LastFrame.init();
        strike(STRIKE_MISS);
        miss(STRIKE_MISS);

        STRIKE_SPARE = LastFrame.init();
        strike(STRIKE_SPARE);
        spare(STRIKE_SPARE);

        SPARE_STRIKE = LastFrame.init();
        spare(SPARE_STRIKE);
        strike(SPARE_STRIKE);

        SPARE_RUNNING = LastFrame.init();
        spare(SPARE_RUNNING);
        SPARE_RUNNING.bowl(FIVE);
    }

    private static void strike(final LastFrame lastFrame) {
        lastFrame.bowl(TEN);
    }

    private static void spare(final LastFrame lastFrame) {
        lastFrame.bowl(FIVE);
        lastFrame.bowl(FIVE);
    }

    private static void miss(final LastFrame lastFrame) {
        lastFrame.bowl(FIVE);
        lastFrame.bowl(TWO);
    }

    @Test
    @DisplayName("초기화")
    void init() {
        assertThatCode(LastFrame::init).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("볼링이 끝났는지")
    void isBowlingEnd(final LastFrame lastFrame, final boolean expected) {
        assertThat(lastFrame.isLastFrameEnd()).isEqualTo(expected);
    }

    private static Stream<Arguments> isBowlingEnd() {
        LastFrame miss = LastFrame.init();
        miss.downPins(Pin.of(0));
        miss.downPins(Pin.of(0));

        LastFrame ready = LastFrame.init();

        LastFrame running = LastFrame.init();
        running.downPins(Pin.of(5));

        LastFrame strike = LastFrame.init();
        strike.downPins(Pin.of(10));

        LastFrame doubleStrike = LastFrame.init();
        doubleStrike.downPins(Pin.of(10));
        doubleStrike.downPins(Pin.of(10));

        LastFrame tripleStrike = LastFrame.init();
        tripleStrike.downPins(Pin.of(10));
        tripleStrike.downPins(Pin.of(10));
        tripleStrike.downPins(Pin.of(10));

        LastFrame spare = LastFrame.init();
        spare.downPins(Pin.of(5));
        spare.downPins(Pin.of(5));

        return Stream.of(
                Arguments.of(miss, true),
                Arguments.of(ready, false),
                Arguments.of(running, false),
                Arguments.of(strike, false),
                Arguments.of(doubleStrike, false),
                Arguments.of(tripleStrike, true),
                Arguments.of(spare, false)
        );
    }

    @Test
    @DisplayName("프레임 추가 시도시 아무것도 안함")
    void appendFrame() {
        assertThatCode(() -> LastFrame.init().appendFrame(new ArrayList<>()))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("3회 던졌거나, 마지막 프레임이 miss 인 경우 isFrameEnd() true, 그렇지 않으면 false")
    void isEnd(final LastFrame lastFrame, final boolean expected) {
        assertThat(lastFrame.isFrameEnd()).isEqualTo(expected);
        assertThat(lastFrame.isFrameEnd()).isEqualTo(lastFrame.isLastFrameEnd());
    }

    private static Stream<Arguments> isEnd() {
        return Stream.of(
                Arguments.of(LastFrame.init(), false),
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
        LastFrame lastFrame = LastFrame.init();

        assertThat(lastFrame.getScore()).isEqualTo(Score.INCALCULABLE);
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("left 가 1인 경우 가장 첫번째 상태의 addBonusScore을 호출하여 해당하는 핀의 갯수만큼만 더하고 리턴한다")
    void addScore(final LastFrame lastFrame, final Score expected) {
        assertThat(lastFrame.addBonusScore(Score.ofSpare())).isEqualTo(expected);
    }

    private static Stream<Arguments> addScore() {
        return Stream.of(
                Arguments.of(LastFrame.init(), Score.ofSpare()),
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
    @DisplayName("left 가 2인 경우 가장 첫번째 상태의 addBonusScore을 호출하여 해당하는 핀의 갯수만큼만 더하고 리턴한다")
    void addScoreWithLeft2(final LastFrame lastFrame, final Score expected) {
        assertThat(lastFrame.addBonusScore(Score.ofStrike())).isEqualTo(expected);
    }

    private static Stream<Arguments> addScoreWithLeft2() {
        return Stream.of(
                Arguments.of(LastFrame.init(), Score.ofStrike()),
                Arguments.of(END_WITH_MISS, Score.of(17, 0)),
                Arguments.of(STRIKE, Score.of(20, 1)),
                Arguments.of(DOUBLE_STRIKE, Score.of(30, 0)),
                Arguments.of(TRIPLE_STRIKE, Score.of(30, 0)),
                Arguments.of(STRIKE_MISS, Score.of(25, 0)),
                Arguments.of(TRY_ONCE, Score.of(15, 1)),
                Arguments.of(SPARE, Score.of(20, 0))
        );
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("마지막 프레임 종료 상태는 점수를 구할 수 있다.")
    void calculateScore(final LastFrame lastFrame, final Score expected) {
        assertThat(lastFrame.getScore()).isEqualTo(expected);
        assertThat(lastFrame.getScore().getScore()).isEqualTo(expected.getScore());
    }

    private static Stream<Arguments> calculateScore() {
        return Stream.of(
                Arguments.of(TRIPLE_STRIKE, Score.of(30)),
                Arguments.of(STRIKE_MISS, Score.of(17)),
                Arguments.of(STRIKE_SPARE, Score.of(20)),
                Arguments.of(SPARE_STRIKE, Score.of(20)),
                Arguments.of(SPARE_RUNNING, Score.of(15))
        );
    }
}