package advent.of.code.tasks.task12;

import java.io.IOException;
import java.util.List;

import advent.of.code.helper.Helper;
import advent.of.code.helper.RunType;

public class Task12 {

    private static final int TASK_NUMBER = 12;

    public static void main(String[] args) throws IOException {
        runMain(RunType.TEST);
        runMain(RunType.REAL);
    }

    private static void runMain(RunType runType) throws IOException {
        System.out.println(runType);
        List<String> cavesList = Helper.convertTxtToStringList(TASK_NUMBER, runType, Helper.LINE_SEPARATOR);
        CaveSystem caveSystem = new CaveSystem(cavesList);

        int validPath = caveSystem.countValidPath();
        System.out.println("Valid path: " + validPath);
        Helper.assertResults(validPath, TASK_NUMBER, 1, runType);

        int validPathWithSingleSmallCaveTwice = caveSystem.countValidPathWithSingleSmallCaveTwice();
        System.out.println("Valid path with single small cave twice: " + validPathWithSingleSmallCaveTwice);
        Helper.assertResults(validPathWithSingleSmallCaveTwice, TASK_NUMBER, 2, runType);
    }
}
