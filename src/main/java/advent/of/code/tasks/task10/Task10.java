package advent.of.code.tasks.task10;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import advent.of.code.helper.Helper;
import advent.of.code.helper.RunType;

public class Task10 {

    private static final int TASK_NUMBER = 10;

    private static final Map<String, String> chunkTypes = Map.of(
        "(", ")",
        "[", "]",
        "{", "}",
        "<", ">"
    );

    private static final Map<String, long[]> AUTO_COMPLETE_VALUES = Map.of(
        ")", new long[] {3, 1},
        "]", new long[] {57, 2},
        "}", new long[] {1197, 3},
        ">", new long[] {25137, 4}
    );

    public static void main(String[] args) throws IOException {
        runMain(RunType.TEST);
        runMain(RunType.REAL);
    }

    private static void runMain(RunType runType) throws IOException {
        System.out.println(runType);
        List<String> chunkList = Helper.convertTxtToStringList(TASK_NUMBER, runType, Helper.LINE_SEPARATOR);

        long totalSyntaxError = getTotalSyntaxError(chunkList);
        System.out.println("Total syntax error: " + totalSyntaxError);
        Helper.assertResults(totalSyntaxError, TASK_NUMBER, 1, runType);

        long lineEndsMiddleScore = getLineEndsMiddleScore(chunkList);
        System.out.println("Line ends middle score: " + lineEndsMiddleScore);
        Helper.assertResults(lineEndsMiddleScore, TASK_NUMBER, 2, runType);
    }

    private static long getTotalSyntaxError(List<String> chunkList) {
        long totalSyntaxError = 0;
        List<String[]> chunkArrays = chunkList.stream()
            .map(s -> s.split(Helper.NONE))
            .collect(Collectors.toList());

        for (String[] chunkLine : chunkArrays) {
            List<String> openedChunks = new ArrayList<>();
            for (String chunkChar : chunkLine) {
                if (chunkTypes.containsValue(chunkChar)) {
                    int lastOpenedIndex = openedChunks.size() - 1;
                    String lastOpened = openedChunks.get(lastOpenedIndex);

                    if (chunkChar.equals(chunkTypes.get(lastOpened))) {
                        openedChunks.remove(lastOpenedIndex);
                    } else {
                        totalSyntaxError += AUTO_COMPLETE_VALUES.get(chunkChar)[0];
                        break;
                    }
                } else {
                    openedChunks.add(chunkChar);
                }
            }
        }

        return totalSyntaxError;
    }

    private static long getLineEndsMiddleScore(List<String> chunkList) {
        List<String[]> lineEnds = getLineEnds(chunkList);

        List<Long> lineEndsValue = lineEnds.stream()
            .map(strings -> Arrays.stream(strings)
                .map(s -> AUTO_COMPLETE_VALUES.get(s)[1])
                .reduce(0L, (a, b) -> Long.sum(a * 5L, b)))
            .sorted()
            .collect(Collectors.toList());

        return lineEndsValue.get((lineEndsValue.size() / 2));
    }

    private static List<String[]> getLineEnds(List<String> chunkList) {
        List<String[]> lineEnds = new ArrayList<>();
        List<String> openedChunks = new ArrayList<>();
        List<String[]> chunkArrays = chunkList.stream()
            .map(s -> s.split(Helper.NONE))
            .collect(Collectors.toList());


        for (String[] chunkLine : chunkArrays) {
            boolean uncorruptedLine = true;
            for (String chunkChar : chunkLine) {
                if (chunkTypes.containsValue(chunkChar)) {
                    int lastOpenedIndex = openedChunks.size() - 1;
                    String lastOpened = openedChunks.get(lastOpenedIndex);

                    if (chunkChar.equals(chunkTypes.get(lastOpened))) {
                        openedChunks.remove(lastOpenedIndex);
                    } else {
                        uncorruptedLine = false;
                        break;
                    }
                } else {
                    openedChunks.add(chunkChar);
                }
            }
            if (uncorruptedLine) {
                Collections.reverse(openedChunks);
                lineEnds.add(openedChunks.stream()
                    .map(chunkTypes::get)

                    .toArray(String[]::new));
            }
            openedChunks.clear();
        }

        return lineEnds;
    }
}
