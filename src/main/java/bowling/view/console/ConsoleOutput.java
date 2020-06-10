package bowling.view.console;

import bowling.dto.PlayerStateDto;
import bowling.dto.ScoreDto;
import bowling.view.state.StatesStringConverter;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import static bowling.domain.frame.Frame.BASE_NUM_OF_FRAME;
import static bowling.domain.frame.Frame.LAST_NUM_OF_FRAME;

public class ConsoleOutput {
    private static final String FRAME_FORMAT = "  %-4s|";
    private static final String SCORE_FORMAT = "  %-4d|";
    private static final String NAME_FORMAT = "| %4s |";
    private static final String NUMBER_FORMAT = "  %02d  |";
    private static final String CUR_PAYER_STATE_FORMAT = "%s의 %d프레임 투구 :";
    private static final String EMPTY_STR = "";

    private ConsoleOutput() {}

    public static void showCurPlayer(final PlayerStateDto player) {
        System.out.print(String.format(CUR_PAYER_STATE_FORMAT, player.getName(), player.getCurFrameNo()));
    }

    public static void showScoreBoard(final List<PlayerStateDto> playersState) {
        showHeader();
        showPlayers(playersState);
        newLine();
    }

    private static void showHeader() {
        System.out.print(String.format(NAME_FORMAT, "NAME"));

        IntStream.rangeClosed(BASE_NUM_OF_FRAME, LAST_NUM_OF_FRAME)
                .forEach(no -> System.out.print(String.format(NUMBER_FORMAT, no)));

        newLine();
    }

    private static void showPlayers(final List<PlayerStateDto> playersState) {
        playersState.forEach(ConsoleOutput::ShowPlayer);
    }

    private static void ShowPlayer(final PlayerStateDto player) {
        showFrameStateOfPlayer(player);
        showScoreOfPlayer(player);
    }

    private static void showScoreOfPlayer(final PlayerStateDto player) {
        System.out.print(String.format(NAME_FORMAT, EMPTY_STR));

        AtomicInteger totalScore = new AtomicInteger(0);
        player.getScores()
                .stream()
                .map(ScoreDto::getScore)
                .map(totalScore::addAndGet)
                .forEach(score -> System.out.print(String.format(SCORE_FORMAT, score)));

        IntStream.range(player.getScores().size(), LAST_NUM_OF_FRAME)
                .forEach(noStr -> System.out.print(String.format(FRAME_FORMAT, EMPTY_STR)));

        newLine();
    }

    private static void showFrameStateOfPlayer(final PlayerStateDto player) {
        System.out.print(String.format(NAME_FORMAT, player.getName()));

        player.getStates()
                .stream()
                .map(StatesStringConverter::convert)
                .forEach(state -> System.out.print(String.format(FRAME_FORMAT, state)));

        IntStream.range(player.getCurFrameNo(), LAST_NUM_OF_FRAME)
                .forEach(noStr -> System.out.print(String.format(FRAME_FORMAT, EMPTY_STR)));

        newLine();
    }

    private static void newLine() {
        System.out.println();
    }
}
