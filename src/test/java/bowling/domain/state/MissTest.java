package bowling.domain.state;

import bowling.domain.pin.Pin;
import bowling.domain.score.Score;
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

    @Test
    @DisplayName("미스 상태에서는 점수를 계산할 수 있다.")
    void getScore() {
        Miss miss = Miss.init(Pin.of(5), Pin.of(2));

        assertThat(miss.calculateScore()).isEqualTo(Score.of(7));
    }

    @Test
    @DisplayName("left 가 1인 경우 첫번째 쓰러뜨린 핀의 개수 만큼만 더하고 리턴한다")
    void addScore() {
        Score score = Score.ofSpare();
        Miss miss = Miss.init(Pin.of(5), Pin.of(2));

        assertThat(miss.addScore(score)).isEqualTo(Score.of(15, 0));
    }

    @Test
    @DisplayName("left 가 2인 경우 첫번째, 두번째 쓰러뜨린 핀의 개수를 모두 더하고 리턴한다")
    void addScoreHavingLeft2() {
        Score score = Score.ofStrike();
        Miss miss = Miss.init(Pin.of(5), Pin.of(2));

        assertThat(miss.addScore(score)).isEqualTo(Score.of(17, 0));
    }
}
