package advent.of.code.tasks.task15;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import advent.of.code.helper.Helper;

public class CavernMap {

    private List<RiskPoint> matrix = new ArrayList<>();
    private final Set<RiskPoint> open = Collections.synchronizedSet(new HashSet<>());
    private final Set<RiskPoint> closed = new HashSet<>();
    private int maxSquireSide;

    public CavernMap(List<String> cavernMatrixRows) {
        for (int i = 0; i < cavernMatrixRows.size(); i++) {
            int[] riskValues = Arrays.stream(cavernMatrixRows.get(i).split(Helper.NONE))
                .mapToInt(Integer::parseInt)
                .toArray();

            for (int j = 0; j < riskValues.length; j++) {
                matrix.add(new RiskPoint(i, j, riskValues[j]));
            }
        }
        matrix = matrix.stream()
            .sorted(Comparator.comparingInt(RiskPoint::getX))
            .collect(Collectors.toList());
        maxSquireSide = getMaxSquireSide();
    }

    public void increaseDimension(int dimensionMultiplier) {
        List<RiskPoint> increasedRight = new ArrayList<>();

        // Increase right
        for (int i = 0; i < dimensionMultiplier - 1; i++) {
            int finalI = i;
            matrix.forEach(riskPoint ->
                increasedRight.add(new RiskPoint(
                    riskPoint.getX(),
                    riskPoint.getY() + ((int) Math.pow(matrix.size(), 0.5) * (finalI + 1)),
                    (riskPoint.getValue() + finalI + 1) > 9 ? (riskPoint.getValue() + finalI + 1) % 9 : (riskPoint.getValue() + finalI + 1)))
            );
        }

        List<RiskPoint> increasedDown = new ArrayList<>();
        for (int i = 0; i < dimensionMultiplier - 1; i++) {
            int finalI = i;
            Stream.of(matrix, increasedRight).flatMap(Collection::stream).forEach(riskPoint ->
                increasedDown.add(new RiskPoint(
                    riskPoint.getX() + ((int) Math.pow(matrix.size(), 0.5) * (finalI + 1)),
                    riskPoint.getY(),
                    (riskPoint.getValue() + finalI + 1) > 9 ? (riskPoint.getValue() + finalI + 1) % 9 : (riskPoint.getValue() + finalI + 1)))
            );
        }

        matrix = Stream.of(matrix, increasedRight, increasedDown)
            .flatMap(Collection::stream)
            .sorted(Comparator.comparingInt(RiskPoint::getX))
            .collect(Collectors.toList());
        maxSquireSide = getMaxSquireSide();
    }

    public int getLowestTotalRiskPath() {
        open.add(new RiskPoint(0, 0, 0));
        RiskPoint endPoint = matrix.get(matrix.size() - 1);

        while (!open.contains(endPoint)) {
            List<RiskPoint> lowestRiskPoints = open.stream()
                .sorted(Comparator.comparingInt(this::getMovementCost))
                .limit(11)
                .collect(Collectors.toList());

            lowestRiskPoints.parallelStream().forEach(this::createAdjacentPoints);
            lowestRiskPoints.forEach(open::remove);
        }

        return open.stream().filter(riskPoint -> riskPoint.equals(endPoint))
            .min(Comparator.comparingInt(RiskPoint::getValue))
            .orElseThrow().getValue();
    }

    private void createAdjacentPoints(RiskPoint lowestRiskPoint) {
        if (!closed.contains(lowestRiskPoint)) {
            closed.add(lowestRiskPoint);
            createNewSuccessor((lowestRiskPoint.getY() + 1) <= maxSquireSide, lowestRiskPoint.getX(), (lowestRiskPoint.getY() + 1), lowestRiskPoint.getValue());
            createNewSuccessor((lowestRiskPoint.getX() + 1) <= maxSquireSide, (lowestRiskPoint.getX() + 1), lowestRiskPoint.getY(), lowestRiskPoint.getValue());
            createNewSuccessor((lowestRiskPoint.getY() - 1) >= 0, lowestRiskPoint.getX(), (lowestRiskPoint.getY() - 1), lowestRiskPoint.getValue());
            createNewSuccessor((lowestRiskPoint.getX() - 1) >= 0, (lowestRiskPoint.getX() - 1), lowestRiskPoint.getY(), lowestRiskPoint.getValue());
        }
    }

    private void createNewSuccessor(boolean predicate, int x, int y, int value) {
        if (predicate) {
            RiskPoint adjacentRiskPoint = matrix.stream()
                .filter(riskPoint -> riskPoint.getX() == x && riskPoint.getY() == y)
                .findFirst().orElseThrow();

            RiskPoint newBranch = new RiskPoint(
                adjacentRiskPoint.getX(),
                adjacentRiskPoint.getY(),
                adjacentRiskPoint.getValue() + value
            );

            synchronized (open) {
                if (open.contains(newBranch)) {
                    RiskPoint riskPoint1;

                    riskPoint1 = open.stream()
                        .filter(riskPoint -> riskPoint.equals(newBranch))
                        .findFirst().orElseThrow();

                    if (newBranch.getValue() < riskPoint1.getValue()) {
                        riskPoint1.setValue(newBranch.getValue());
                    }

                } else {
                    open.add(newBranch);
                }
            }
        }
    }

    private int getMovementCost(RiskPoint riskPoint) {
        return Math.abs(riskPoint.getX() - maxSquireSide) + Math.abs(riskPoint.getY() - maxSquireSide) + riskPoint.getValue();
    }

    private int getMaxSquireSide() {
        return matrix.stream()
            .mapToInt(RiskPoint::getX)
            .max().orElseThrow();
    }
}
