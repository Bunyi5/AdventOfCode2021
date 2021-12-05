package advent.of.code.tasks.task5;

public class OceanFloorMap {

    private final int[][] map;

    public OceanFloorMap(int[][] map) {
        this.map = map;
    }

    public void drawVerticalOrHorizontalVectorOnMap(Vector vector) {
        int fromX = vector.getFromX();
        int whereX = vector.getWhereX();
        int fromY = vector.getFromY();
        int whereY = vector.getWhereY();

        if (fromX > whereX) {
            int temp = fromX;
            fromX = whereX;
            whereX = temp;
        }

        if (fromY > whereY) {
            int temp = fromY;
            fromY = whereY;
            whereY = temp;
        }

        for (int i = fromY; i <= whereY; i++) {
            for (int j = fromX; j <= whereX; j++) {
                map[i][j]++;
            }
        }
    }
    public void drawDiagonalVectorOnMap(Vector vector) {
        int fromX = vector.getFromX();
        int whereX = vector.getWhereX();
        int fromY = vector.getFromY();
        int whereY = vector.getWhereY();

        while (fromX != whereX && fromY != whereY) {
            map[fromY][fromX]++;
            if (fromX > whereX) {
                fromX--;
            } else {
                fromX++;
            }
            if (fromY > whereY) {
                fromY--;
            } else {
                fromY++;
            }
        }
        map[fromY][fromX]++;
    }

    public int countDangerousAreas() {
        int count = 0;

        for (int[] rows : map) {
            for (int point : rows) {
                if (point > 1) {
                    count++;
                }
            }
        }

        return count;
    }

    public void printOceanFloorMap() {
        for (int[] ints : map) {
            for (int anInt : ints) {
                if (anInt == 0) {
                    System.out.print(". ");
                } else {
                    System.out.print(anInt + " ");
                }
            }
            System.out.println();
        }
    }
}
