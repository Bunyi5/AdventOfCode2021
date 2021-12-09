package advent.of.code.tasks.task8;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import advent.of.code.helper.Helper;
import advent.of.code.helper.RunType;

public class Task8 {

    private static final int TASK_NUMBER = 8;

    public static void main(String[] args) throws IOException {
        runMain(RunType.TEST);
        runMain(RunType.REAL);
    }

    private static void runMain(RunType runType) throws IOException {
        System.out.println(runType);
        List<String> signalPatternsAndOutputs = Helper.convertTxtToStringList("input" + TASK_NUMBER + runType + ".txt", Helper.LINE_SEPARATOR);

        List<List<String>> signalPatterns = getSignalPatternsOrOutputs(signalPatternsAndOutputs, 0);
        List<List<String>> outputValues = getSignalPatternsOrOutputs(signalPatternsAndOutputs, 1);

        int uniqueValues = countUniqueValues(outputValues);
        System.out.println("1, 4, 7, 8 values appear time: " + uniqueValues);

        int sumOfOutputValues = getSumOfOutputValues(signalPatterns, outputValues);
        System.out.println("Sum of output values: " + sumOfOutputValues);
    }

    private static int getSumOfOutputValues(List<List<String>> signalPatterns, List<List<String>> outputValues) {
        int sum = 0;

        for (int i = 0; i < signalPatterns.size(); i++) {
            SevenSegmentDisplayDecoder sevenSegmentDisplayDecoder = new SevenSegmentDisplayDecoder();
            List<String> signalPattern = signalPatterns.get(i);
            List<String> outputValue = outputValues.get(i);

            sevenSegmentDisplayDecoder.setResultChars(signalPattern);
            sevenSegmentDisplayDecoder.fillResultTable();

            sum += sevenSegmentDisplayDecoder.convertOutputValuesToInt(outputValue);
        }

        return sum;
    }

    private static List<List<String>> getSignalPatternsOrOutputs(List<String> signalPatternsAndOutputs, int firstOrSecond) {
        return signalPatternsAndOutputs.stream()
            .map(signal -> signal.split(Helper.WHITESPACE + Helper.PIPE + Helper.WHITESPACE)[firstOrSecond])
            .map(value -> Arrays.asList(value.split(Helper.WHITESPACE)))
            .collect(Collectors.toList());
    }

    private static int countUniqueValues(List<List<String>> outputValues) {
        int counter = 0;

        for (List<String> outputValueRows : outputValues) {
            for (String outputValue : outputValueRows) {
                if (List.of(2, 4, 3, 7).contains(outputValue.length())) {
                    counter++;
                }
            }
        }

        return counter;
    }
}
