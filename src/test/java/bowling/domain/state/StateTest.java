package bowling.domain.state;

import bowling.domain.pin.Pin;
import bowling.domain.score.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

@DisplayName("상태 추상클래스 테스트")
class StateTest {
    private static final State STATE = new State() {
        @Override
        public State processDownPins(Pin downPins) {
            return null;
        }

        @Override
        public List<Integer> getDownPins() {
            return null;
        }

        @Override
        protected Score add(Score prevScore) {
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
