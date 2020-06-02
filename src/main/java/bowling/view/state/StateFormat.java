package bowling.view.state;

import bowling.domain.state.*;
import bowling.dto.StateDto;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum StateFormat {
    READY(Ready.class, state -> ""),
    RUNNING(Running.class, state -> String.format("%s", Symbol.convert(state.getFirstDownPins()))),
    SPARE(Spare.class, state -> String.format("%s|/", Symbol.convert(state.getFirstDownPins()))),
    STRIKE(Strike.class, state -> String.format("%s", Symbol.convert(state.getFirstDownPins()))),
    MISS(Miss.class, state -> String.format("%s|%s",
            Symbol.convert(state.getFirstDownPins()), Symbol.convert(state.getSecondDownPins())));

    private static final Map<Class<? extends State>, StateFormat> STATE_FORMATS =
            Arrays.stream(values())
                    .collect(Collectors.toMap(StateFormat::getStateType, Function.identity()));

    private final Class<? extends State> stateType;
    private final Function<StateDto, String> converter;

    StateFormat(final Class<? extends State> stateType, final Function<StateDto, String> converter) {
        this.stateType = stateType;
        this.converter = converter;
    }

    public static String convert(final StateDto stateDto) {
        if (!STATE_FORMATS.containsKey(stateDto.getStateClass())) {
            throw new IllegalArgumentException("Can't find format of " + stateDto.getStateClass());
        }

        return STATE_FORMATS.get(stateDto.getStateClass())
                .converter.apply(stateDto);
    }

    private Class<? extends State> getStateType() {
        return stateType;
    }
}
