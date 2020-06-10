package bowling.dto;

import bowling.domain.player.BowlingPlayer;

import java.util.List;

public class PlayerStateDto {
    private final String name;
    private final int curFrameNo;
    private final List<StateDtos> states;
    private final List<ScoreDto> scores;

    private PlayerStateDto(final BowlingPlayer player) {
        this.name = player.getName();
        this.states = player.getStates();
        this.scores = player.getScores();
        this.curFrameNo = states.size();
    }

    public static PlayerStateDto of(final BowlingPlayer player) {
        return new PlayerStateDto(player);
    }

    public String getName() {
        return name;
    }

    public int getCurFrameNo() {
        return curFrameNo;
    }

    public List<StateDtos> getStates() {
        return states;
    }

    public List<ScoreDto> getScores() {
        return scores;
    }
}
