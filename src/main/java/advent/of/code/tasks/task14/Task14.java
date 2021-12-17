package advent.of.code.tasks.task14;

import java.io.IOException;

import advent.of.code.helper.Helper;
import advent.of.code.helper.RunType;

public class Task14 {

    private static final int TASK_NUMBER = 14;

    public static void main(String[] args) throws IOException {
        runMain(RunType.TEST);
        runMain(RunType.REAL);
    }

    private static void runMain(RunType runType) throws IOException {
        System.out.println(runType);
        String polymerContent = Helper.convertTxtToStringContent(TASK_NUMBER, runType);

        Polymer polymer = new Polymer(polymerContent);
        polymer.doStep(10);
        long quantityOfTheMostAndLeastCommon = polymer.quantityOfTheMostAndLeastCommon();
        System.out.println("Quantity of the most and least common after step 10: " + quantityOfTheMostAndLeastCommon);
        Helper.assertResults(quantityOfTheMostAndLeastCommon, TASK_NUMBER, 1, runType);

        Polymer polymer2 = new Polymer(polymerContent);
        polymer2.doStep(40);
        long quantityOfTheMostAndLeastCommon40 = polymer2.quantityOfTheMostAndLeastCommon();
        System.out.println("Quantity of the most and least common after step 40: " + quantityOfTheMostAndLeastCommon40);
        Helper.assertResults(quantityOfTheMostAndLeastCommon40, TASK_NUMBER, 2, runType);
    }

}
