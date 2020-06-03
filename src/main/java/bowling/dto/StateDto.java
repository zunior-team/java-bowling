package bowling.dto;

import bowling.domain.state.State;

public class StateDto {
    private static final int INDEX_OF_FIRST_DOWN_PINS = 0;
    private static final int INDEX_OF_SECOND_DOWN_PINS = 1;

    private final State state;

    private StateDto(final State state) {
        this.state = state;
    }

    public static StateDto of(final State state) {
        return new StateDto(state);
    }

    public Class<? extends State> getStateClass() {
        return state.getClass();
    }

    public int getFirstDownPins() {
        verify(INDEX_OF_FIRST_DOWN_PINS);

        return state.getDownPins()
                .get(INDEX_OF_FIRST_DOWN_PINS);
    }

    public int getSecondDownPins() {
        verify(INDEX_OF_SECOND_DOWN_PINS);

        return state.getDownPins()
                .get(INDEX_OF_SECOND_DOWN_PINS);
    }

    private void verify(final int index) {
        if (state.getDownPins().size() <= index) {
            throw new IllegalArgumentException(
                    String.format("Can't access down pins of %d", index)
            );
        }
    }
}
