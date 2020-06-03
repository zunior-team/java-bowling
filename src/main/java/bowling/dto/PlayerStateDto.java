package bowling.dto;

import bowling.domain.player.BowlingPlayer;

import java.util.List;

public class PlayerStateDto {
    private final String name;
    private final int curFrameNo;
    private final List<StateDtos> states;

    private PlayerStateDto(final BowlingPlayer player) {
        this.name = player.getName();
        this.states = player.getStates();
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
}
