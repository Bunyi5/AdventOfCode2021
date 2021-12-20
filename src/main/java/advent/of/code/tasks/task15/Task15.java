package advent.of.code.tasks.task15;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import advent.of.code.helper.Helper;
import advent.of.code.helper.RunType;
import advent.of.code.helper.Timer;

public class Task15 {

    private static final int TASK_NUMBER = 15;

    public static void main(String[] args) throws IOException {
        runMain(RunType.TEST);
        runMain(RunType.REAL);
    }

    private static void runMain(RunType runType) throws IOException {
        System.out.println(runType);
        List<String> cavernMatrixRows = Helper.convertTxtToStringList(TASK_NUMBER, runType, Helper.LINE_SEPARATOR);

        Timer.startTimer();
        CavernMap cavernMap = new CavernMap(cavernMatrixRows);
        int lowestTotalRiskPath = cavernMap.getLowestTotalRiskPath();
        System.out.println("Lowest total risk path: " + lowestTotalRiskPath);
        Helper.assertResults(lowestTotalRiskPath, TASK_NUMBER, 1, runType);
        Timer.endTimer(TimeUnit.SECONDS);

        Timer.startTimer();
        CavernMap cavernMap5 = new CavernMap(cavernMatrixRows);
        cavernMap5.increaseDimension(5);
        int lowestTotalRiskPath5 = cavernMap5.getLowestTotalRiskPath();
        System.out.println("Lowest total risk path with five times dimension: " + lowestTotalRiskPath5);
        Helper.assertResults(lowestTotalRiskPath5, TASK_NUMBER, 2, runType);
        Timer.endTimer(TimeUnit.SECONDS);
    }
}
