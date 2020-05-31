package bowling.state;

import bowling.pin.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;

@DisplayName("미스 상태 테스트")
class MissTest {

    @Test
    @DisplayName("초기화는 두개의 Pin 정보를 가지고 할수 있다")
    void init() {
        assertThatCode(() -> Miss.init(Pin.of(5), Pin.of(1))).doesNotThrowAnyException();
    }

}