package bowling.domain.state;

import bowling.domain.pin.Pin;
import bowling.domain.score.Score;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@DisplayName("준비 상태")
@SuppressWarnings("ResultOfMethodCallIgnored")
class ReadyTest {
    private Ready READY;

    @BeforeEach
    void setting() {
        READY = Ready.instance();
    }

    @Test
    @DisplayName("종료 상태는 false")
    void isEnd() {
        assertThat(READY.isEnd()).isFalse();
    }

    @Test
    @DisplayName("10개를 쓰러뜨리면 Strike 를 반환")
    void down10Pins() {
        assertThat(READY.downPins(Pin.of(10))).isInstanceOf(Strike.class);
    }

    @Test
    @DisplayName("10개 미만을 쓰러뜨린경우 Running 반환")
    void downUnder10Pins() {
        assertThat(READY.downPins(Pin.of(9))).isInstanceOf(Running.class);
    }

    @Test
    @DisplayName("싱글톤 적용 instance")
    void instance() {
        assertThatCode(Ready::instance).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("싱글톤이기 때문에 equals 결과가 true")
    void equals() {
        assertThat(Ready.instance()).isEqualTo(Ready.instance());
    }

    @Test
    @DisplayName("상태 가져오기")
    void getState() {
        State readyState = Ready.instance()
                .getState()
                .get(0);

        assertThat(readyState).isInstanceOf(Ready.class);
    }

    @Test
    @DisplayName("넘어진 핀 갯수 가져오기")
    void getDownPins() {
        State readyState = Ready.instance();

        assertThat(readyState.getDownPins()).isEmpty();
    }

    @Test
    @DisplayName("준비 상태는 점수 계산을 할 수 없다")
    void getScore() {
        Running running = Running.init(Pin.of(5));

        assertThat(running.getScore()).isEqualTo(Score.INCALCULABLE);
    }

    @Test
    @DisplayName("준비 상태에서는 점수를 더하더라도 아무 변화가 없다")
    void addScore() {
        Score score = Score.ofSpare(); //left != 0
        Ready ready = Ready.instance();

        assertThat(ready.addScore(score)).isEqualTo(score);
    }

    @Test
    @DisplayName("모돈 상태에서 left 가 0 인경우 원래 score 를 리턴한다")
    void addScoreWithLeftCountIsZero() {
        Score score = Score.of(5);
        Ready ready = Ready.instance();

        assertThat(ready.addScore(score)).isEqualTo(score);
    }
}
