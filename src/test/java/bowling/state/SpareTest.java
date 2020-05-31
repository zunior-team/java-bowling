package bowling.state;

import bowling.pin.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;

@DisplayName("스페어 상태 테스트")
class SpareTest {

    @Test
    @DisplayName("스페어는 하나의 핀 객체를 가지고 초기화한다")
    void init() {
        assertThatCode(() -> Spare.init(Pin.of(5))).doesNotThrowAnyException();
    }
}