package bowling.frame;

import bowling.exception.UnReachableStateException;
import bowling.pin.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

@DisplayName("마지막 프레임 테스트")
class LastFrameTest {

    @Test
    @DisplayName("초기화")
    void init() {
        assertThatCode(LastFrame::init).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("볼링이 끝났는지")
    void isBowlingEnd(final LastFrame lastFrame, final boolean expected) {
        assertThat(lastFrame.isBowlingEnd()).isEqualTo(expected);
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
}