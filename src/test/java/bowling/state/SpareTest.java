package bowling.state;

import bowling.pin.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

@DisplayName("스페어 상태 테스트")
class SpareTest {

    @Test
    @DisplayName("스페어는 두개의 핀 객체를 가지고 초기화한다")
    void init() {
        assertThatCode(() -> Spare.init(Pin.of(5), Pin.of(5))).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("핀의 합이 핀의 토탈 갯수가 아닌 경우에는 예외가 발생한다")
    void initFailSumIsNotSameWithTotalPins() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Spare.init(Pin.of(5), Pin.of(2)));
    }
}