package advent.of.code.tasks.task6;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import advent.of.code.helper.Helper;

public class Task6 {

    private static final int DAYS80 = 80;
    private static final int DAYS256 = 256;

    private static final int FISH_DEFAULT_INITIAL_VALUE = 9;
    private static final int FISH_DEFAULT_RESPAWN_VALUE = 7;

    public static void main(String[] args) throws IOException {
        String stringList = Helper.convertTxtToStringContent("input6-test.txt");

        List<Integer> lanternFishList = Arrays.stream(stringList.split(","))
            .map(Integer::parseInt)
            .collect(Collectors.toList());

        long populateSum80 = lanternFishList.parallelStream()
                .map(fishInitialValue ->
                    populateLanternFishRecursively(fishInitialValue, 0L, DAYS80)
                ).reduce(0L, Long::sum);
        System.out.println("Lantern fish population after " + DAYS80 + " days: " + populateSum80);

        // Runs for at least 10 minutes
        long populateSum256 = lanternFishList.parallelStream()
            .map(fishInitialValue ->
                populateLanternFishRecursively(fishInitialValue, 0L, DAYS256)
            ).reduce(0L, Long::sum);
        System.out.println("Lantern fish population after " + DAYS256 + " days: " + populateSum256);
    }

    private static long populateLanternFishRecursively(int fishInitialValue, long populateSum, int days) {
        int remainingDays = days - fishInitialValue;
        populateSum++;

        while (remainingDays > 0) {

            if ((remainingDays - FISH_DEFAULT_INITIAL_VALUE) > 0) {
                populateSum = populateLanternFishRecursively(FISH_DEFAULT_INITIAL_VALUE, populateSum, remainingDays);
            } else {
                populateSum++;
            }

            remainingDays -= FISH_DEFAULT_RESPAWN_VALUE;
        }

        return populateSum;
    }
}
