package advent.of.code.tasks.task13;

import java.io.IOException;

import advent.of.code.helper.Helper;
import advent.of.code.helper.RunType;

public class Task13 {

    private static final int TASK_NUMBER = 13;

    public static void main(String[] args) throws IOException {
        runMain(RunType.TEST);
        runMain(RunType.REAL);
    }

    private static void runMain(RunType runType) throws IOException {
        System.out.println(runType);
        String paperContext = Helper.convertTxtToStringContent(TASK_NUMBER, runType);

        TransparentPaper transparentPaper1 = new TransparentPaper(paperContext);
        transparentPaper1.foldByLine(transparentPaper1.getInstructions().get(0));
        int dots = transparentPaper1.countDots();
        System.out.println("Visible dots after first fold: " + dots);
        Helper.assertResults(dots, TASK_NUMBER, 1, runType);

        TransparentPaper transparentPaper2 = new TransparentPaper(paperContext);
        transparentPaper2.getInstructions().forEach(transparentPaper2::foldByLine);
        transparentPaper2.printPaper();
    }
}
