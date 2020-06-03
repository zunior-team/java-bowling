package bowling.view.state;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum Symbol {
    ZERO(0, "-"),
    TEN(10, "X");

    private static final Map<Integer, String> SYMBOLS =
            Arrays.stream(values())
                    .collect(Collectors.toMap(Symbol::getNumber, Symbol::getSymbol));
    private final int number;
    private final String symbol;

    Symbol(final int number, final String symbol) {
        this.number = number;
        this.symbol = symbol;
    }

    public static String convert(final int number) {
        return SYMBOLS.getOrDefault(number, String.valueOf(number));
    }

    private int getNumber() {
        return number;
    }

    private String getSymbol() {
        return symbol;
    }
}
