package bowling.pin;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@DisplayName("볼링핀에 대한 테스트")
class PinTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 10})
    @DisplayName("볼링핀 초기화")
    void init(final int fallenPins) {
        assertThatCode(() -> Pin.of(fallenPins))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("equals: 싱글톤 패턴 적용")
    void equals() {
        assertThat(Pin.of(0)).isEqualTo(Pin.of(0));
    }

}