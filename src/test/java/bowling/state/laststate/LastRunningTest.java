package bowling.state.laststate;

import bowling.pin.Pin;
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
        LastRunning END_WITH_MISS = LastRunning.init();
        END_WITH_MISS.downPins(Pin.of(5));
        END_WITH_MISS.downPins(Pin.of(2));

        LastRunning STRIKE = LastRunning.init();
        STRIKE.downPins(Pin.of(10));

        LastRunning DOUBLE_STRIKE = LastRunning.init();
        DOUBLE_STRIKE.downPins(Pin.of(10));
        DOUBLE_STRIKE.downPins(Pin.of(10));

        LastRunning TRIPLE_STRIKE = LastRunning.init();
        TRIPLE_STRIKE.downPins(Pin.of(10));
        TRIPLE_STRIKE.downPins(Pin.of(10));
        TRIPLE_STRIKE.downPins(Pin.of(10));

        LastRunning STRIKE_MISS = LastRunning.init();
        STRIKE_MISS.downPins(Pin.of(10));
        STRIKE_MISS.downPins(Pin.of(5));
        STRIKE_MISS.downPins(Pin.of(2));

        LastRunning TRY_ONCE = LastRunning.init();
        TRY_ONCE.downPins(Pin.of(5));

        LastRunning SPARE = LastRunning.init();
        SPARE.downPins(Pin.of(5));
        SPARE.downPins(Pin.of(5));


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
}
