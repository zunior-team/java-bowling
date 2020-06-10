package bowling.domain.score;

import bowling.exception.UnCalculatableException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("점수 테스트")
class ScoreTest {

    @Test
    @DisplayName("초기화")
    void init() {
        assertThatCode(() -> Score.of(0)).doesNotThrowAnyException();
        assertThatCode(() -> Score.ofSpare()).doesNotThrowAnyException();
        assertThatCode(() -> Score.ofStrike()).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("of 로 생성된 score 는 더 이상 더할 수 없으며, 더하려고 하면 예외가 발생한다.")
    void of() {
        Score score = Score.of(10);

        assertThatThrownBy(() -> score.add(5)).isInstanceOf(UnCalculatableException.class);
    }

    @Test
    @DisplayName("spare 로 생성된 score 는 한번 더할수 있다.")
    void ofSpare() {
        Score score = Score.ofSpare();

        assertThatCode(() -> score.add(1)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("spare 로 생성된 score 는 두번 이상 더할 수 없다.")
    void ofSpareAddMoreThanOnce() {
        Score score = Score.ofSpare()
                .add(5);

        assertThatThrownBy(() -> score.add(5)).isInstanceOf(UnCalculatableException.class);
    }
}