package bowling.view.console;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ConsoleInput {
    private static final String SPLITTER = ",";
    private static final String INPUT_PLAYERS_STATEMENT = "플레이어 이름은(3 english letters)?: ";

    private static final Scanner SCANNER = new Scanner(System.in);

    private ConsoleInput() {}

    public static List<String> inputPlayers() {
        System.out.print(INPUT_PLAYERS_STATEMENT);

        String[] names = SCANNER.nextLine().split(SPLITTER);

        return Stream.of(names)
                .map(String::trim)
                .map(String::toUpperCase)
                .collect(Collectors.toList());
    }

    public static int inputDownPins() {
        return inputInt();
    }

    private static int inputInt() {
        return Integer.parseInt(SCANNER.nextLine());
    }
}
