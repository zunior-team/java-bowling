package bowling.domain.state;

import bowling.domain.score.Score;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@SuppressWarnings("ResultOfMethodCallIgnored")
@DisplayName("스트라이크 테스트")
class StrikeTest {
    private Strike STRIKE;

    @BeforeEach
    void setting() {
        STRIKE = Strike.instance();
    }

    @Test
    @DisplayName("종료 상태는 true")
    void isEnd() {
        assertThat(STRIKE.isEnd()).isTrue();
    }

    @Test
    @DisplayName("싱글톤으로 객체 가져오기")
    void instance() {
        assertThatCode(Strike::instance).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("항상 같은 객체를 리턴한다")
    void equals() {
        assertThat(Strike.instance()).isEqualTo(Strike.instance());
    }

    @Test
    @DisplayName("상태 가져오기")
    void getState() {
        State strikeState = Strike.instance()
                .getState()
                .get(0);

        assertThat(strikeState).isInstanceOf(Strike.class);
    }

    @Test
    @DisplayName("넘어진 핀 갯수 가져오기")
    void getDownPins() {
        State strike = Strike.instance();

        assertThat(strike.getDownPins()).containsExactly(10);
    }

    @Test
    @DisplayName("스트라이크 상태는 점수를 구하기 위해 추가적인 정보가 더 필요하다")
    void getScore() {
        Strike strike = Strike.instance();

        assertThat(strike.calculateScore().isCalculable()).isFalse();
    }

    @Test
    @DisplayName("left 가 1인 경우 10을 더하고 리턴한다")
    void addScore() {
        Score score = Score.ofSpare();
        Strike strike = Strike.instance();

        assertThat(strike.addScore(score)).isEqualTo(Score.of(20, 0));
    }

    @Test
    @DisplayName("left 가 2인 경우 첫번째, 10을 더하고 리턴한다")
    void addScoreHavingLeft2() {
        Score score = Score.ofStrike();
        Strike strike = Strike.instance();

        assertThat(strike.addScore(score)).isEqualTo(Score.of(20, 1));
    }
}
