package bowling.domain.player;

import bowling.dto.PlayerStateDto;

import java.util.List;
import java.util.stream.Collectors;

public class BowlingPlayers {
    private int curPlayerIdx; // nextPlayer = curPlayer + 1 % players.size();
    private final List<BowlingPlayer> players;

    private BowlingPlayers(final List<String> names) {
        validate(names);

        players = names.stream()
                .map(String::trim)
                .map(BowlingPlayer::init)
                .collect(Collectors.toList());
    }

    private void validate(List<String> names) {
        if (names == null || names.isEmpty()) {
            throw new IllegalArgumentException("Player names are null or empty");
        }
    }

    public static BowlingPlayers init(final List<String> names) {
        return new BowlingPlayers(names);
    }

    public void rotatePlayer() {
        curPlayerIdx++;
        curPlayerIdx %= players.size();
    }

    public boolean isGameEnd() {
        return players.stream()
                .allMatch(BowlingPlayer::isGameEnd);
    }

    public BowlingPlayer curPlayer() {
        return players.get(curPlayerIdx);
    }

    public List<PlayerStateDto> getPlayersState() {
        return players.stream()
                .map(PlayerStateDto::of)
                .collect(Collectors.toList());
    }
}
