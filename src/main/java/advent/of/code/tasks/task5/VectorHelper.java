package advent.of.code.tasks.task5;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import advent.of.code.helper.Helper;

public class VectorHelper {

    public static List<Vector> convertVectorStringListToVectorList(List<String> vectorStringList) {
        List<Vector> vectorList = new ArrayList<>();

        for (String lines : vectorStringList) {
            String[] fromAndWhere = lines.split(" -> ");

            String from = fromAndWhere[0];
            String where = fromAndWhere[1];

            String[] fromXY = from.split(Helper.COMMA);
            String[] whereXY = where.split(Helper.COMMA);

            int fromX = Integer.parseInt(fromXY[0]);
            int fromY = Integer.parseInt(fromXY[1]);

            int whereX = Integer.parseInt(whereXY[0]);
            int whereY = Integer.parseInt(whereXY[1]);

            Vector vector = new Vector(fromX, fromY, whereX, whereY);

            vectorList.add(vector);
        }

        return vectorList;
    }

    public static int getBiggestHorizontal(List<Vector> vectorList) {
        int biggest = 0;

        for (Vector vector : vectorList) {
            if (vector.getFromX() > biggest) {
                biggest = vector.getFromX();
            }
            if (vector.getWhereX() > biggest) {
                biggest = vector.getWhereX();
            }
        }

        return biggest + 1;
    }

    public static int getBiggestVertical(List<Vector> vectorList) {
        int biggest = 0;

        for (Vector vector : vectorList) {
            if (vector.getFromY() > biggest) {
                biggest = vector.getFromY();
            }
            if (vector.getWhereY() > biggest) {
                biggest = vector.getWhereY();
            }
        }

        return biggest + 1;
    }

    public static List<Vector> filterOutDiagonalVectors(List<Vector> vectorList) {
        return vectorList.stream()
            .filter(vector -> vector.getFromX() == vector.getWhereX() ||
                vector.getFromY() == vector.getWhereY()
            ).collect(Collectors.toList());
    }

    public static List<Vector> filterOutVerticalAndHorizontalVectors(List<Vector> vectorList) {
        return vectorList.stream()
            .filter(vector -> vector.getFromX() != vector.getWhereX() &&
                vector.getFromY() != vector.getWhereY()
            ).collect(Collectors.toList());
    }
}
