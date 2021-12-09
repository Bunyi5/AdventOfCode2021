package advent.of.code.tasks.task9;

import java.io.IOException;

import advent.of.code.helper.Helper;
import advent.of.code.helper.RunType;

public class Task9 {

    private static final int TASK_NUMBER = 9;

    public static void main(String[] args) throws IOException {
        runMain(RunType.TEST);
        runMain(RunType.REAL);
    }

    private static void runMain(RunType runType) throws IOException {
        System.out.println(runType);
        int[][] heightMapArray = Helper.convertTxtToIntArray("input" + TASK_NUMBER + runType + ".txt", Helper.NONE);
        HeightMap heightMap = new HeightMap(heightMapArray);

        int sumOfRiskLevels = heightMap.getSumOfRiskLevels();
        System.out.println("Sum of risk levels: " + sumOfRiskLevels);
        Helper.assertResults(sumOfRiskLevels, TASK_NUMBER, 1, runType);

        int threeLargestBasinMultiplied = heightMap.getThreeLargestBasinMultiplied();
        System.out.println("Three largest basin multiplied: " + threeLargestBasinMultiplied);
        Helper.assertResults(threeLargestBasinMultiplied, TASK_NUMBER, 2, runType);
    }
}
