package bowling.state;

import bowling.pin.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

@DisplayName("상태 추상클래스 테스트")
class StateTest {
    private static final State STATE = new State() {
        @Override
        public State processDownPins(Pin downPins) {
            return null;
        }
    };

    @Test
    @DisplayName("Pin 이 null 일 경우 예외 발생")
    void downPins() {

        assertThatIllegalArgumentException()
                .isThrownBy(() -> STATE.downPins(null));
    }

    @Test
    @DisplayName("일반 상태는 끝난 상태가 아니다")
    void isEnd() {
        assertThat(STATE.isEnd()).isFalse();
    }
}
