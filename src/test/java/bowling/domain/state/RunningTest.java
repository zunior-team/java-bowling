package bowling.domain.state;

import bowling.domain.pin.Pin;
import bowling.domain.score.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@DisplayName("실행중 상태 테스트")
class RunningTest {
    private static final Running RUNNING = Running.init(Pin.of(5));

    @Test
    @DisplayName("초기화")
    void init() {
        assertThatCode(() -> Running.init(Pin.of(5))).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("핀 쓰러뜨리기 성공")
    void downPins() {
        assertThatCode(() -> RUNNING.downPins(Pin.of(5))).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("모든 핀을 쓰러뜨리면 Spare 반환")
    void downPinsAllPinsDown() {
        assertThat(RUNNING.downPins(Pin.of(5))).isInstanceOf(Spare.class);
    }

    @Test
    @DisplayName("모든 핀을 쓰러뜨리지 못하면 Miss 반환")
    void downPinsButPinsRemain() {
        assertThat(RUNNING.downPins(Pin.of(2))).isInstanceOf(Miss.class);
    }

    @Test
    @DisplayName("상태 가져오기")
    void getState() {
        State runningState = Running.init(Pin.of(5))
                .getState()
                .get(0);

        assertThat(runningState).isInstanceOf(Running.class);
    }

    @Test
    @DisplayName("넘어진 핀 갯수 가져오기")
    void getDownPins() {
        State runningState = Running.init(Pin.of(5));

        assertThat(runningState.getDownPins()).containsExactly(5);
    }

    @Test
    @DisplayName("프레임 진행중에는 점수를 계산할 수 없다")
    void getScore() {
        Ready ready = Ready.instance();

        assertThat(ready.calculateScore()).isEqualTo(Score.INCALCULABLE);
    }


    @Test
    @DisplayName("score 를 더하면 본인이 가지고 있는 pin 만큼 더한 값을 리턴한다")
    void addScore() {
        Score score = Score.ofSpare();
        Running running = Running.init(Pin.of(5));

        assertThat(running.addScore(score)).isEqualTo(Score.of(15, 0));
    }

    @Test
    @DisplayName("score 를 더하면 left 카운트가 하나 줄어든다.")
    void addScoreOfStrike() {
        Score score = Score.ofStrike();
        Running running = Running.init(Pin.of(5));

        assertThat(running.addScore(score)).isEqualTo(Score.of(15, 1));
    }

}
