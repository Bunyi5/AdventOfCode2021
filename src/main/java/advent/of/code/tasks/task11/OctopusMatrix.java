package advent.of.code.tasks.task11;

import java.util.Arrays;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class OctopusMatrix {

    private final Octopus[][] matrix;

    private final Predicate<Integer> isUpOutOfBounds;
    private final Predicate<Integer> isDownOutOfBounds;
    private final Predicate<Integer> isLeftOutOfBounds;
    private final Predicate<Integer> isRightOutOfBounds;

    private final BiPredicate<Integer, Integer> isUpLeftOutOfBounds;
    private final BiPredicate<Integer, Integer> isUpRightOutOfBounds;
    private final BiPredicate<Integer, Integer> isDownLeftOutOfBounds;
    private final BiPredicate<Integer, Integer> isDownRightOutOfBounds;

    public OctopusMatrix(Octopus[][] matrix) {
        this.matrix = matrix;

        isUpOutOfBounds = i -> (i - 1 >= 0);
        isDownOutOfBounds = i -> (i + 1 < matrix.length);
        isLeftOutOfBounds = j -> (j - 1 >= 0);
        isRightOutOfBounds = j -> (j + 1 < matrix[0].length);

        // Diagonal
        isUpLeftOutOfBounds = (i, j) -> isUpOutOfBounds.test(i) && isLeftOutOfBounds.test(j);
        isUpRightOutOfBounds = (i, j) -> (isUpOutOfBounds.test(i) && isRightOutOfBounds.test(j));
        isDownLeftOutOfBounds = (i, j) -> (isDownOutOfBounds.test(i) && isLeftOutOfBounds.test(j));
        isDownRightOutOfBounds = (i, j) -> (isDownOutOfBounds.test(i) && isRightOutOfBounds.test(j));
    }

    public int getTotalFlash(int steps) {
        int totalFlash = 0;

        for (int n = 0; n < steps; n++) {
            increaseAllOctopusEnergyByOne();

            flashOctopuses();

            totalFlash += countFlashes();

            resetFlash();
        }

        return totalFlash;
    }

    public int getSynchronizedStep() {
        int synchronizedStep = 0;
        boolean isSynchronized = false;
        while (!isSynchronized) {
            increaseAllOctopusEnergyByOne();

            flashOctopuses();

            synchronizedStep++;
            isSynchronized = checkSynchronized();

            resetFlash();
        }

        return synchronizedStep;
    }

    private void flashOctopuses() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                Octopus octopus = matrix[i][j];
                if (!octopus.isAlreadyFlashed() && (octopus.getEnergy() > 9)) {
                    octopus.setAlreadyFlashed(true);
                    increaseAllAdjacentOctopusEnergy(i, j);
                }
            }
        }
    }

    private void increaseAllAdjacentOctopusEnergy(int i, int j) {
        increaseAdjacentOctopusEnergy(isUpOutOfBounds.test(i), i - 1, j);
        increaseAdjacentOctopusEnergy(isDownOutOfBounds.test(i), i + 1, j);
        increaseAdjacentOctopusEnergy(isLeftOutOfBounds.test(j), i, j - 1);
        increaseAdjacentOctopusEnergy(isRightOutOfBounds.test(j), i, j + 1);

        // Diagonal
        increaseAdjacentOctopusEnergy(isUpLeftOutOfBounds.test(i, j), i - 1, j - 1);
        increaseAdjacentOctopusEnergy(isUpRightOutOfBounds.test(i, j), i - 1, j + 1);
        increaseAdjacentOctopusEnergy(isDownLeftOutOfBounds.test(i, j), i + 1, j - 1);
        increaseAdjacentOctopusEnergy(isDownRightOutOfBounds.test(i, j), i + 1, j + 1);
    }

    private void increaseAdjacentOctopusEnergy(boolean statement, int row, int column) {
        if (statement) {
            matrix[row][column].increaseEnergyByOne();

            if (!matrix[row][column].isAlreadyFlashed() && (matrix[row][column].getEnergy() > 9)) {
                matrix[row][column].setAlreadyFlashed(true);
                increaseAllAdjacentOctopusEnergy(row, column);
            }
        }
    }

    private void increaseAllOctopusEnergyByOne() {
        Arrays.stream(matrix)
            .flatMap(Arrays::stream)
            .forEach(Octopus::increaseEnergyByOne);
    }

    private int countFlashes() {
        return (int) Arrays.stream(matrix)
            .flatMap(Arrays::stream)
            .filter(Octopus::isAlreadyFlashed)
            .count();
    }

    private void resetFlash() {
        Arrays.stream(matrix)
            .flatMap(Arrays::stream)
            .filter(Octopus::isAlreadyFlashed)
            .forEach(octopus -> {
                octopus.setEnergy(0);
                octopus.setAlreadyFlashed(false);
            });
    }

    private boolean checkSynchronized() {
        return Arrays.stream(matrix)
            .flatMap(Arrays::stream)
            .allMatch(Octopus::isAlreadyFlashed);
    }
}
