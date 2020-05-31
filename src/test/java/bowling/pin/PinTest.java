package bowling.pin;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static bowling.pin.Pin.MAXIMUM_SIZE_OF_PIN;
import static bowling.pin.Pin.MINIMUM_SIZE_OF_PIN;
import static org.assertj.core.api.Assertions.*;

@DisplayName("볼링핀에 대한 테스트")
class PinTest {

    @ParameterizedTest
    @ValueSource(ints = {MINIMUM_SIZE_OF_PIN, MAXIMUM_SIZE_OF_PIN})
    @DisplayName("볼링핀 초기화")
    void init(final int fallenPins) {
        assertThatCode(() -> Pin.of(fallenPins))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("equals: 싱글톤 패턴 적용")
    void equals() {
        final int fallenPins = 0;

        assertThat(Pin.of(fallenPins)).isEqualTo(Pin.of(fallenPins));
    }

    @ParameterizedTest
    @ValueSource(ints = {MINIMUM_SIZE_OF_PIN - 1, MAXIMUM_SIZE_OF_PIN + 1})
    @DisplayName("초기화 실패")
    void initFail(final int fallenPins) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Pin.of(fallenPins));
    }

    @Test
    @DisplayName("더하기")
    void add() {
        assertThat(Pin.of(2).add(Pin.of(5))).isEqualTo(Pin.of(7));
    }

    @Test
    @DisplayName("더하기 실패: 범위 초과")
    void addFail() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Pin.of(5).add(Pin.of(10)));
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("모든 핀이 쓰러졌는지?")
    void isAllDown(final Pin pin, final boolean expected) {
        assertThat(pin.isAllDown()).isEqualTo(expected);
    }

    private static Stream<Arguments> isAllDown() {
        return Stream.of(
                Arguments.of(Pin.of(10), true),
                Arguments.of(Pin.of(0), false)
        );
    }

}