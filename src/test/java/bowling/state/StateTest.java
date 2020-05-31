package bowling.state;

import bowling.pin.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

@DisplayName("상태 추상클래스 테스트")
class StateTest {

    @Test
    @DisplayName("Pin 이 null 일 경우 예외 발생")
    void downPins() {
        State state = new State() {
            @Override
            State processDownPins(Pin downPins) {
                return null;
            }
        };

        assertThatIllegalArgumentException()
                .isThrownBy(() -> state.downPins(null));
    }
}