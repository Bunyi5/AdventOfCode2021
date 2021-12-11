package advent.of.code.tasks.task11;

import java.io.IOException;

import advent.of.code.helper.Helper;
import advent.of.code.helper.RunType;

public class Task11 {

    private static final int TASK_NUMBER = 11;

    private static final int STEP100 = 100;

    public static void main(String[] args) throws IOException {
        runMain(RunType.TEST);
        runMain(RunType.REAL);
    }

    private static void runMain(RunType runType) throws IOException {
        System.out.println(runType);
        int[][] octopusEnergy = Helper.convertTxtToIntArray(TASK_NUMBER, runType, Helper.NONE);

        OctopusMatrix octopusMatrix = new OctopusMatrix(convertArrayToOctopusMatrix(octopusEnergy));
        int totalFlash = octopusMatrix.getTotalFlash(STEP100);
        System.out.println("Total flashes after " + STEP100 + " steps: " + totalFlash);
        Helper.assertResults(totalFlash, TASK_NUMBER, 1, runType);

        OctopusMatrix octopusMatrix2 = new OctopusMatrix(convertArrayToOctopusMatrix(octopusEnergy));
        int synchronizedStep = octopusMatrix2.getSynchronizedStep();
        System.out.println("Synchronized step: " + synchronizedStep);
        Helper.assertResults(synchronizedStep, TASK_NUMBER, 2, runType);
    }

    private static Octopus[][] convertArrayToOctopusMatrix(int[][] octopusEnergy) {
        Octopus[][] matrix = new Octopus[octopusEnergy.length][octopusEnergy[0].length];

        for (int i = 0; i < octopusEnergy.length; i++) {
            for (int j = 0; j < octopusEnergy[0].length; j++) {
                matrix[i][j] = new Octopus(octopusEnergy[i][j]);
            }
        }

        return matrix;
    }

}
