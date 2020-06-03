package bowling.domain.player;

import bowling.domain.frame.Frames;
import bowling.domain.pin.Pin;
import bowling.dto.StateDtos;

import java.util.List;

public class BowlingPlayer {
    private final Player player;
    private final Frames frames;

    private BowlingPlayer(final String name) {
        this.player = Player.init(name);
        this.frames = Frames.init();
    }

    public static BowlingPlayer init(final String name) {
        return new BowlingPlayer(name);
    }

    public void play(final int numOfDownPin) {
        frames.downPins(Pin.of(numOfDownPin));
    }

    public boolean isGameEnd() {
        return frames.isAllFrameEnd();
    }

    public String getName() {
        return player.getName();
    }

    public List<StateDtos> getStates() {
        return frames.getTotalStates();
    }
}
