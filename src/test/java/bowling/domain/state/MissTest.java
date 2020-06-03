package bowling.domain.state;

import bowling.domain.pin.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("미스 상태 테스트")
class MissTest {

    @Test
    @DisplayName("초기화는 두개의 Pin 정보를 가지고 할수 있다")
    void init() {
        assertThatCode(() -> Miss.init(Pin.of(5), Pin.of(1))).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("초기화시 두 핀의 합이 핀의 총 합과 같을땐 예외를 발생시킨다")
    void initFail() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Miss.init(Pin.of(5), Pin.of(5)));
    }

    @Test
    @DisplayName("상태 가져오기")
    void getState() {
        State missState = Miss.init(Pin.of(5), Pin.of(2))
                .getState()
                .get(0);

        assertThat(missState).isInstanceOf(Miss.class);
    }

    @Test
    @DisplayName("넘어진 핀 갯수 가져오기")
    void getDownPins() {
        State missState = Miss.init(Pin.of(5), Pin.of(2));

        assertThat(missState.getDownPins()).containsExactly(5, 2);
    }
}
