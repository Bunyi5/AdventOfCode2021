package advent.of.code.tasks.task7;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import advent.of.code.helper.Helper;

public class Task7 {

    public static void main(String[] args) throws IOException {
        List<Integer> crabPositions = Helper.convertTxtToIntList("input7.txt", Helper.COMMA);

        List<Integer> positions = generatePositions(crabPositions);

        int bestFuel = calculateBestFuelOption(crabPositions, positions, false);

        int bestExpensiveFuel = calculateBestFuelOption(crabPositions, positions, true);

        System.out.println("Best fuel: " + bestFuel);
        System.out.println("Best expensive fuel: " + bestExpensiveFuel);
    }

    private static int calculateBestFuelOption(List<Integer> crabPositions, List<Integer> positions, boolean isExpensive) {
        int bestFuel = 2147483647;

        for (Integer position : positions) {
            int fuel;
            if (isExpensive) {
                fuel = calculateExpensiveFuelByPosition(crabPositions, position);
            } else {
                fuel = calculateFuelByPosition(crabPositions, position);
            }

            if (bestFuel > fuel) {
                bestFuel = fuel;
            } else {
                break;
            }

        }

        return bestFuel;
    }

    private static int calculateFuelByPosition(List<Integer> crabPositions, int position) {
        return crabPositions.stream()
            .map(crabPosition -> Math.abs(crabPosition - position))
            .mapToInt(Integer::intValue)
            .sum();
    }

    private static int calculateExpensiveFuelByPosition(List<Integer> crabPositions, int position) {
        return crabPositions.stream()
            .map(crabPosition -> sumOfDigitsFrom1ToN(Math.abs(crabPosition - position)))
            .mapToInt(Integer::intValue)
            .sum();
    }

    private static List<Integer> generatePositions(List<Integer> crabPositions) {
        Collections.sort(crabPositions);

        int lowestPosition = crabPositions.get(0);
        int largestPosition = crabPositions.get(crabPositions.size() - 1);

        return IntStream.rangeClosed(lowestPosition, largestPosition).boxed().collect(Collectors.toList());
    }

    private static int sumOfDigitsFrom1ToN(int n) {
        int sum = 0;

        for (int x = 1; x <= n; x++) {
            sum += x;
        }

        return sum;
    }
}
