package advent.of.code.tasks.task3;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import advent.of.code.helper.Helper;
import advent.of.code.helper.RunType;

public class Task3 {

    private static final int TASK_NUMBER = 3;

    public static void main(String[] args) throws IOException {
        runMain(RunType.TEST);
        runMain(RunType.REAL);
    }

    private static void runMain(RunType runType) throws IOException {
        System.out.println(runType);
        List<String> binaryList = Helper.convertTxtToStringList("input" + TASK_NUMBER + runType + ".txt", Helper.LINE_SEPARATOR);

        int powerConsumption = calculatePowerConsumption(binaryList);
        System.out.println("Power consumption: " + powerConsumption);
        Helper.assertResults(powerConsumption, TASK_NUMBER, 1, runType);

        int lifeSupportRatingResult = calculateLifeSupportRatingResult(binaryList);
        System.out.println("Life support rating: " + lifeSupportRatingResult);
        Helper.assertResults(lifeSupportRatingResult, TASK_NUMBER, 2, runType);
    }

    private static int calculatePowerConsumption(List<String> binaryList) {
        int binaryNumberLength = binaryList.get(0).length();
        StringBuilder gammaRateString = new StringBuilder();
        StringBuilder epsilonRateString = new StringBuilder();

        for (int i = 0; i < binaryNumberLength; i++) {
            int ones = 0;
            int zeros = 0;

            for (String binary : binaryList) {
                char num = binary.charAt(i);

                if (num == '1') {
                    ones++;
                } else {
                    zeros++;
                }
            }

            if (ones > zeros) {
                gammaRateString.append(1);
                epsilonRateString.append(0);
            } else {
                gammaRateString.append(0);
                epsilonRateString.append(1);
            }
        }

        int gammaRate = Integer.parseInt(gammaRateString.toString(), 2);
        int epsilonRate = Integer.parseInt(epsilonRateString.toString(), 2);

        return gammaRate * epsilonRate;
    }

    private static int calculateLifeSupportRatingResult(List<String> binaryList) {

        int oxygen = calculateLifeSupportRating(binaryList, LifeSupportType.OXYGEN_GENERATOR);
        int co2 = calculateLifeSupportRating(binaryList, LifeSupportType.CO2_SCRUBBER);

        return oxygen * co2;
    }

    private static int calculateLifeSupportRating(List<String> binaryList, LifeSupportType lifeSupportType) {
        int binaryNumberLength = binaryList.get(0).length();

        for (int i = 0; i < binaryNumberLength; i++) {
            int ones = 0;
            int zeros = 0;

            for (String binary : binaryList) {
                int num = binary.charAt(i);

                if (num == '1') {
                    ones++;
                } else {
                    zeros++;
                }
            }

            int finalI = i;
            char filterParam;
            if (lifeSupportType.apply(ones, zeros)) {
                filterParam = '1';
            } else {
                filterParam = '0';
            }

            binaryList = binaryList.stream()
                .filter(binary -> binary.charAt(finalI) == filterParam)
                .collect(Collectors.toList());

            if (binaryList.size() == 1) {
                break;
            }
        }

        return Integer.parseInt(binaryList.get(0), 2);
    }
}
