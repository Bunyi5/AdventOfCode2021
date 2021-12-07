package advent.of.code.tasks.task1;

import java.io.IOException;
import java.util.List;

import advent.of.code.helper.Helper;
import advent.of.code.helper.RunType;

public class Task1 {

    private static final int TASK_NUMBER = 1;

    public static void main(String[] args) throws IOException {
        runMain(RunType.TEST);
        runMain(RunType.REAL);
    }

    public static void runMain(RunType runType) throws IOException {
        System.out.println(runType);
        List<Integer> intListTest = Helper.convertTxtToIntList("input" + TASK_NUMBER + runType + ".txt", Helper.LINE_SEPARATOR);

        int oneMeasurement = doOneMeasurement(intListTest);
        System.out.println("One measurement: " + oneMeasurement);
        Helper.assertResults(oneMeasurement, TASK_NUMBER, 1, runType);

        int threeMeasurement = doThreeMeasurement(intListTest);
        System.out.println("Three measurement test: " + threeMeasurement);
        Helper.assertResults(threeMeasurement, TASK_NUMBER, 2, runType);
    }

    private static int doOneMeasurement(List<Integer> intList) {
        int previous = intList.get(0);
        int next;
        int counter = 0;

        for (Integer integer : intList) {
            next = integer;

            if (next > previous) {
                counter++;
            }

            previous = next;
        }

        return counter;
    }

    private static int doThreeMeasurement(List<Integer> intList) {
        int previous = intList.get(0) + intList.get(1) + intList.get(2);
        int next;
        int counter = 0;

        for (int i = 1; i < intList.size() - 2; i++) {
            next = intList.get(i) + intList.get(i + 1) + intList.get(i + 2);

            if (next > previous) {
                counter++;
            }

            previous = next;
        }

        return counter;
    }

}
