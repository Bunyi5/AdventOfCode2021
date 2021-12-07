package advent.of.code.tasks.task4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import advent.of.code.helper.Helper;

public class BingoConverter {

    public static List<Integer> createDrawnNumbers(String bingoContent) {
        String[] split = bingoContent.split("(" + Helper.LINE_SEPARATOR +"){2}");

        return Arrays.stream(split[0].split(Helper.COMMA))
            .map(Integer::parseInt)
            .collect(Collectors.toList());
    }

    public static List<BingoTable> createBingoTables(String bingoContent) {
        List<BingoTable> bingoTables = new ArrayList<>();

        List<List<String>> bingoTablesString = divideBingoAllToTables(bingoContent);

        for (List<String> bingoTableString : bingoTablesString) {
            BingoNumber[][] table = new BingoNumber[5][5];

            for (int i = 0; i < bingoTableString.size(); i++) {

                List<Integer> bingoTableRow = Arrays.stream(bingoTableString.get(i).split(Helper.WHITESPACE))
                    .filter(s -> !s.isEmpty())
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());

                for (int j = 0; j < 5; j++) {
                    table[i][j] = new BingoNumber(false, bingoTableRow.get(j));
                }
            }

            bingoTables.add(new BingoTable(table));
        }

        return bingoTables;
    }

    private static List<List<String>> divideBingoAllToTables(String bingoContent) {
        List<List<String>> result = new ArrayList<>();
        List<String> bingoTablesString = Arrays.stream(bingoContent.split("(" + Helper.LINE_SEPARATOR + "){2}")).collect(Collectors.toList());
        bingoTablesString.remove(0);

        for (String bingoTableString : bingoTablesString) {
            List<String> bingoTableRows = Arrays.stream(bingoTableString.split(Helper.LINE_SEPARATOR)).collect(Collectors.toList());
            result.add(bingoTableRows);
        }

        return result;
    }
}
