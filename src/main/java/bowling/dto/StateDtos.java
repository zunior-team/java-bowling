package bowling.dto;

import bowling.domain.state.State;

import java.util.List;
import java.util.stream.Collectors;

public class StateDtos {
    private final List<StateDto> stateDtos;

    public StateDtos(final List<State> state) {
        stateDtos = state.stream()
                .map(StateDto::of)
                .collect(Collectors.toList());
    }

    public static StateDtos of(final List<State> state) {
        return new StateDtos(state);
    }

    public List<StateDto> getStateDtos() {
        return stateDtos;
    }
}
