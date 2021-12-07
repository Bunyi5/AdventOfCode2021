package advent.of.code.tasks.task5;

import java.io.IOException;
import java.util.List;

import advent.of.code.helper.Helper;
import advent.of.code.helper.RunType;

public class Task5 {

    private static final int TASK_NUMBER = 5;

    public static void main(String[] args) throws IOException {
        runMain(RunType.TEST);
        runMain(RunType.REAL);
    }

    private static void runMain(RunType runType) throws IOException {
        System.out.println(runType);
        List<String> vectorStringList = Helper.convertTxtToStringList("input" + TASK_NUMBER + runType + ".txt", Helper.LINE_SEPARATOR);
        List<Vector> vectorList = VectorHelper.convertVectorStringListToVectorList(vectorStringList);

        OceanFloorMap oceanFloorMap = new OceanFloorMap(
            new int[VectorHelper.getBiggestVertical(vectorList)][VectorHelper.getBiggestHorizontal(vectorList)]);

        drawVerticalAndHorizontalVectors(oceanFloorMap, vectorList);
        int dangerousAreasWithoutDiagonal = oceanFloorMap.countDangerousAreas();
        System.out.println("Dangerous ares without diagonal: " + dangerousAreasWithoutDiagonal);
        Helper.assertResults(dangerousAreasWithoutDiagonal, TASK_NUMBER, 1, runType);

        drawDiagonalVectors(oceanFloorMap, vectorList);
        int dangerousAreasWithDiagonal = oceanFloorMap.countDangerousAreas();
        System.out.println("Dangerous ares with diagonal: " + dangerousAreasWithDiagonal);
        Helper.assertResults(dangerousAreasWithDiagonal, TASK_NUMBER, 2, runType);
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
