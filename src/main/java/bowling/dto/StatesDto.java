package bowling.dto;

import bowling.domain.state.State;

import java.util.List;

public class StatesDto<T extends State> {
    List<T> stateDtos;

    public List<T> getStateDtos() {
        return stateDtos;
    }
}
