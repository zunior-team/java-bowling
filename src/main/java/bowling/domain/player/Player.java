package bowling.domain.player;

public class Player {
    public static final int LENGTH_OF_PLAYER_NAME = 3;

    private final String name;

    private Player(final String name) {
        validate(name);

        this.name = name;
    }

    private void validate(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Player name is null or empty");
        }

        if (name.length() != LENGTH_OF_PLAYER_NAME) {
            throw new IllegalArgumentException("Player name must be " + LENGTH_OF_PLAYER_NAME + " english letter");
        }
    }

    public static Player init(final String name) {
        return new Player(name);
    }

    public String getName() {
        return name;
    }
}
