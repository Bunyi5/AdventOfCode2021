package advent.of.code.tasks.task6;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import advent.of.code.helper.Helper;
import advent.of.code.helper.RunType;

public class Task6 {

    private static final int TASK_NUMBER = 6;

    private static final int DAYS80 = 80;
    private static final int DAYS256 = 256;

    public static void main(String[] args) throws IOException {
        runMain(RunType.TEST);
        runMain(RunType.REAL);
    }

    private static void runMain(RunType runType) throws IOException {
        System.out.println(runType);
        List<Integer> lanternFishList = Helper.convertTxtToIntList(TASK_NUMBER, runType, Helper.COMMA);

        long populateSum80 = populateLanternFish(lanternFishList, DAYS80);
        System.out.println("Lantern fish population after " + DAYS80 + " days: " + populateSum80);
        Helper.assertResults(populateSum80, TASK_NUMBER, 1, runType);

        long populateSum256 = populateLanternFish(lanternFishList, DAYS256);
        System.out.println("Lantern fish population after " + DAYS256 + " days: " + populateSum256);
        Helper.assertResults(populateSum256, TASK_NUMBER, 2, runType);
    }

    private static long populateLanternFish(List<Integer> lanternFishList, int days) {
        long[] lanternFishArray = new long[10];

        for (Integer integer : lanternFishList) {
            lanternFishArray[integer]++;
        }

        for (int i = 0; i < days - 1; i++) {

            System.arraycopy(lanternFishArray, 1, lanternFishArray, 0, lanternFishArray.length - 1);

            lanternFishArray[7] += lanternFishArray[0];
            lanternFishArray[9] = lanternFishArray[0];
            lanternFishArray[0] = 0;
        }

        return Arrays.stream(lanternFishArray).reduce(0, Long::sum);
    }
}
