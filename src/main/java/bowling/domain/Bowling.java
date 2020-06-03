package bowling.domain;

import bowling.domain.player.BowlingPlayer;
import bowling.domain.player.BowlingPlayers;
import bowling.dto.PlayerStateDto;

import java.util.List;

public class Bowling {
    private final BowlingPlayers players;

    private Bowling(final List<String> names) {
        players = BowlingPlayers.init(names);
    }

    public static Bowling init(final List<String> names) {
        return new Bowling(names);
    }

    public boolean isGameEnd() {
        return players.isGameEnd();
    }

    public BowlingPlayer curPlayer() {
        return players.curPlayer();
    }

    public void rotatePlayer() {
        players.rotatePlayer();
    }

    public List<PlayerStateDto> getPlayersState() {
        return players.getPlayersState();
    }
}
