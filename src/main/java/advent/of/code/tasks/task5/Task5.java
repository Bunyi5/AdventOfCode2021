package advent.of.code.tasks.task5;

import java.io.IOException;
import java.util.List;

import advent.of.code.helper.Helper;

public class Task5 {

    public static void main(String[] args) throws IOException {
        List<String> vectorStringList = Helper.convertTxtToStringList("input5.txt", Helper.LINE_SEPARATOR);
        List<Vector> vectorList = VectorHelper.convertVectorStringListToVectorList(vectorStringList);

        OceanFloorMap oceanFloorMap = new OceanFloorMap(
            new int[VectorHelper.getBiggestVertical(vectorList)][VectorHelper.getBiggestHorizontal(vectorList)]);

        drawVerticalAndHorizontalVectors(oceanFloorMap, vectorList);
        System.out.println("Dangerous ares without diagonal: " + oceanFloorMap.countDangerousAreas());

        drawDiagonalVectors(oceanFloorMap, vectorList);
        System.out.println("Dangerous ares with diagonal: " + oceanFloorMap.countDangerousAreas());
    }

    private static void drawVerticalAndHorizontalVectors(OceanFloorMap oceanFloorMap, List<Vector> vectorList) {
        List<Vector> verticalAndHorizontalVectors = VectorHelper.filterOutDiagonalVectors(vectorList);
        for (Vector vector : verticalAndHorizontalVectors) {
            oceanFloorMap.drawVerticalOrHorizontalVectorOnMap(vector);
        }
    }

    private static void drawDiagonalVectors(OceanFloorMap oceanFloorMap, List<Vector> vectorList) {
        List<Vector> verticalAndHorizontalVectors = VectorHelper.filterOutVerticalAndHorizontalVectors(vectorList);
        for (Vector vector : verticalAndHorizontalVectors) {
            oceanFloorMap.drawDiagonalVectorOnMap(vector);
        }
    }
}
