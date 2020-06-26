package bowling.domain.frame;

import bowling.domain.pin.Pin;
import bowling.dto.ScoreDto;
import bowling.exception.UnReachableStateException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

@DisplayName("프레임 집합에 대한 테스트")
class FramesTest {

    @Test
    @DisplayName("초기화")
    void init() {
        assertThatCode(Frames::init).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("볼링공 던지기, 볼링 종료 테스트")
    void downPins() {
        Frames frames = Frames.init();

        for (int i = 0 ; i < 11 ; ++i) { // 한 게임에서 스트라이크는 최대 12번 던질 수 있다.
            frames.bowl(Pin.of(10));
            assertThat(frames.isAllFrameEnd()).isFalse();
        }

        frames.bowl(Pin.of(10));
        assertThat(frames.isAllFrameEnd()).isTrue();

        assertThatExceptionOfType(UnReachableStateException.class)
                .isThrownBy(() -> frames.bowl(Pin.of(10)));
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("계속 스트라이크를 던졌을때 점수 계산이 올바르게 되는지")
    void getScoreWithStrikeOnly(final int loopCount, final List<Integer> expected) {
        Frames frames = Frames.init();

        for (int i = 0 ; i < loopCount ; ++i) {
            frames.bowl(Pin.of(10));
        }

        AtomicInteger sum = new AtomicInteger(0);
        List<Integer> scores = frames.getScores()
                .stream()
                .map(ScoreDto::getScore)
                .map(sum::addAndGet)
                .collect(Collectors.toList());

        assertThat(scores).isEqualTo(expected);
    }

    private static Stream<Arguments> getScoreWithStrikeOnly() {
        return Stream.of(
                Arguments.of(1, Collections.EMPTY_LIST),
                Arguments.of(2, Collections.EMPTY_LIST),
                Arguments.of(3, Collections.singletonList(30)),
                Arguments.of(4, texture(2)),
                Arguments.of(5, texture(3)),
                Arguments.of(6, texture(4)),
                Arguments.of(7, texture(5)),
                Arguments.of(8, texture(6)),
                Arguments.of(9, texture(7)),
                Arguments.of(10, texture(8)),
                Arguments.of(11, texture(9)),
                Arguments.of(12, texture(10))
        );
    }

    private static List<Integer> texture(int loopCount) {
        List<Integer> texture = new ArrayList<>();

        int base = 30;
        int interval = 30;

        for (int i = 0 ; i < loopCount ; ++i) {
            texture.add(base + (interval * i));
        }

        return texture;
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("쓰러뜨린 핀의 개수를 받았을때 예상한 점수와 같은지 시뮬레이션")
    void getScoreWithSeveralType(final List<Integer> downPins, final List<Integer> expected) {
        Frames frames = Frames.init();

        downPins.forEach(downPinCount -> frames.bowl(Pin.of(downPinCount)));

        AtomicInteger sum = new AtomicInteger(0);
        List<Integer> scores = frames.getScores()
                .stream()
                .map(ScoreDto::getScore)
                .map(sum::addAndGet)
                .collect(Collectors.toList());

        assertThat(scores).isEqualTo(expected);
    }

    private static Stream<Arguments> getScoreWithSeveralType() {
        List<Integer> downPins = Collections.emptyList();
        List<Integer> scores = Collections.emptyList();

        return Stream.of(
                //  1    2    3    4   5   6   7   8   9    10
                // 0/5  5/5  2/5  10  5/2  10  10  10  0/0  555
                //  5   17   24   41  48   78  98  108 108  123
                Arguments.of(downPins = texture(downPins, 0), scores),
                Arguments.of(downPins = texture(downPins, 5), scores = texture(scores, 5)), // miss state
                Arguments.of(downPins = texture(downPins, 5), scores),
                Arguments.of(downPins = texture(downPins, 5), scores), // spare
                Arguments.of(downPins = texture(downPins, 2), scores = texture(scores, 17)),
                Arguments.of(downPins = texture(downPins, 5), scores = texture(scores, 24)),
                Arguments.of(downPins = texture(downPins, 10), scores),
                Arguments.of(downPins = texture(downPins, 5), scores),
                Arguments.of(downPins = texture(downPins, 2), scores = texture(texture(scores, 41), 48)),
                Arguments.of(downPins = texture(downPins, 10), scores),
                Arguments.of(downPins = texture(downPins, 10), scores),
                Arguments.of(downPins = texture(downPins, 10), scores = texture(scores, 78)),
                Arguments.of(downPins = texture(downPins, 0), scores = texture(scores, 98)),
                Arguments.of(downPins = texture(downPins, 0), scores = texture(texture(scores, 108), 108)),
                Arguments.of(downPins = texture(downPins, 5), scores),
                Arguments.of(downPins = texture(downPins, 5), scores),
                Arguments.of(downPins = texture(downPins, 5), scores = texture(scores, 123))
        );
    }

    private static List<Integer> texture(final List<Integer> origin, final Integer number) {
        if (number == null) {
            return origin;
        }

        List<Integer> newer = new ArrayList<>(origin);

        newer.add(number);
        return newer;
    }
}