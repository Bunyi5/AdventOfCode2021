package advent.of.code.tasks.task9;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;


public class HeightMap {

    private final int[][] map;

    private final Predicate<Integer> isUpOutOfBounds;
    private final Predicate<Integer> isDownOutOfBounds;
    private final Predicate<Integer> isLeftOutOfBounds;
    private final Predicate<Integer> isRightOutOfBounds;


    public HeightMap(int[][] map) {
        this.map = map;

        isUpOutOfBounds = i -> (i - 1 >= 0);
        isDownOutOfBounds = i -> (i + 1 < map.length);
        isLeftOutOfBounds = j -> (j - 1 >= 0);
        isRightOutOfBounds = j -> (j + 1 < map[0].length);
    }

    public int getSumOfRiskLevels() {
        List<Point> lowPoints = determineLowPoints();

        return lowPoints.stream()
            .map(point -> map[point.x][point.y])
            .map(integer -> integer + 1)
            .reduce(0, Integer::sum);
    }

    private List<Point> determineLowPoints() {
        List<Point> lowPoints = new ArrayList<>();

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                int current = map[i][j];

                boolean upBigger = checkAdjacent(isUpOutOfBounds.test(i), i - 1, j, current);
                boolean downBigger = checkAdjacent(isDownOutOfBounds.test(i), i + 1, j, current);
                boolean leftBigger = checkAdjacent(isLeftOutOfBounds.test(j), i, j - 1, current);
                boolean rightBigger = checkAdjacent(isRightOutOfBounds.test(j), i, j + 1, current);

                if (upBigger && downBigger && leftBigger && rightBigger) {
                    lowPoints.add(new Point(i, j));
                }
            }
        }

        return lowPoints;
    }

    private boolean checkAdjacent(boolean statement, int row, int column, int current) {
        if (statement) {
            return map[row][column] > current;
        }

        return true;
    }

    public int getThreeLargestBasinMultiplied() {
        List<Integer> basinsSum = new ArrayList<>();
        List<Point> lowPoints = determineLowPoints();
        List<Point> alreadyCheckedPoints = new ArrayList<>();

        for (Point lowPoint : lowPoints) {
            List<Point> basins = getBasinsRecursively(lowPoint, alreadyCheckedPoints);

            basinsSum.add(basins.size());
            basins.clear();
        }

        return basinsSum.stream()
            .sorted(Comparator.reverseOrder())
            .limit(3)
            .reduce(1, (a, b) -> a * b);
    }

    private List<Point> getBasinsRecursively(Point startPoint, List<Point> alreadyCheckedPoints) {
        int row = startPoint.x;
        int column = startPoint.y;
        int current = map[row][column];
        alreadyCheckedPoints.add(startPoint);

        alreadyCheckedPoints = checkAdjacent(row - 1, column, isUpOutOfBounds.test(row), current, alreadyCheckedPoints);
        alreadyCheckedPoints = checkAdjacent(row + 1, column, isDownOutOfBounds.test(row), current, alreadyCheckedPoints);
        alreadyCheckedPoints = checkAdjacent(row, column - 1, isLeftOutOfBounds.test(column), current, alreadyCheckedPoints);
        alreadyCheckedPoints = checkAdjacent(row, column + 1, isRightOutOfBounds.test(column), current, alreadyCheckedPoints);

        return alreadyCheckedPoints;
    }

    private List<Point> checkAdjacent(int row, int column, boolean statement, int current, List<Point> alreadyCheckedPoints) {
        if (statement) {
            int pontValue = map[row][column];
            Point point = new Point(row, column);

            if (pontValue != 9 && pontValue > current && !alreadyCheckedPoints.contains(point)) {
                alreadyCheckedPoints = getBasinsRecursively(point, alreadyCheckedPoints);
            }
        }

        return alreadyCheckedPoints;
    }
}
