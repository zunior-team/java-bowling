package bowling;

import bowling.domain.Bowling;
import bowling.domain.player.BowlingPlayer;
import bowling.dto.PlayerStateDto;
import bowling.view.console.ConsoleInput;
import bowling.view.console.ConsoleOutput;

import java.util.List;

public class BowlingGame {
    public static void main(String[] args) {
        List<String> names = ConsoleInput.inputPlayers();

        Bowling bowling = Bowling.init(names);
        ConsoleOutput.showScoreBoard(bowling.getPlayersState());

        while(!bowling.isGameEnd()) {
            BowlingPlayer curPlayer = bowling.curPlayer();
            ConsoleOutput.showCurPlayer(PlayerStateDto.of(curPlayer));

            int numOfDownPins = ConsoleInput.inputDownPins();
            curPlayer.play(numOfDownPins);

            ConsoleOutput.showScoreBoard(bowling.getPlayersState());

            bowling.rotatePlayer();
        }
    }
}
