package advent.of.code.helper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Helper {

    private static final String RESOURCE_PATH = "src/main/resources/";

    public static final String LINE_SEPARATOR = "\\r\\n";
    public static final String COMMA = ",";
    public static final String WHITESPACE = "\\s+";
    public static final String PIPE = "[|]";
    public static final String NONE = "";

    public static String convertTxtToStringContent(int taskNumber, RunType runType) throws IOException {
        Path path = Path.of(RESOURCE_PATH, "input" + taskNumber + runType + ".txt");
        return Files.readString(path);
    }

    public static List<Integer> convertTxtToIntList(int taskNumber, RunType runType, String separator) throws IOException {
        String content = convertTxtToStringContent(taskNumber, runType);

        return Arrays.stream(content.split(separator))
            .map(Integer::parseInt)
            .collect(Collectors.toList());
    }

    public static List<String> convertTxtToStringList(int taskNumber, RunType runType, String separator) throws IOException {
        String content = convertTxtToStringContent(taskNumber, runType);

        return Arrays.stream(content.split(separator))
            .collect(Collectors.toList());
    }

    public static int[][] convertTxtToIntArray(int taskNumber, RunType runType, String separator) throws IOException {
        List<String> stringList = convertTxtToStringList(taskNumber, runType, Helper.LINE_SEPARATOR);

        return stringList.stream()
            .map(row -> row.split(separator))
            .map(strings -> Arrays.stream(strings).mapToInt(Integer::parseInt).toArray())
            .toArray(int[][]::new);
    }

    public static void assertResults(Object a, int taskNumber, int partTaskNumber, RunType runType) {
        String reset = "\033[0m";
        String red = "\033[0;31m";
        String green = "\033[0;32m";

        Object b = Answers.valueOf("TASK_" + taskNumber + "_" + partTaskNumber + "_" + runType).getAnswer();

        if (!a.equals(b)) {
            System.out.println(red + "The result is not right!" + reset);
        } else {
            System.out.println(green + "The result is right!" + reset);
        }
    }
}
