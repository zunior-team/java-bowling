package bowling.domain.player;

public class PlayerName {
    public static final int LENGTH_OF_PLAYER_NAME = 3;

    private final String name;

    private PlayerName(final String name) {
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

    public static PlayerName init(final String name) {
        return new PlayerName(name);
    }

    public String getName() {
        return name;
    }
}
