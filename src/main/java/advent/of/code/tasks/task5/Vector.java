package advent.of.code.tasks.task5;

public class Vector {

    private int fromX;
    private int fromY;
    private int whereX;
    private int whereY;

    public Vector(int fromX, int fromY, int whereX, int whereY) {
        this.fromX = fromX;
        this.fromY = fromY;
        this.whereX = whereX;
        this.whereY = whereY;
    }

    public int getFromX() {
        return fromX;
    }

    public int getFromY() {
        return fromY;
    }

    public int getWhereX() {
        return whereX;
    }

    public int getWhereY() {
        return whereY;
    }

    @Override
    public String toString() {
        return "Vector{" +
            "fromX=" + fromX +
            ", fromY=" + fromY +
            ", whereX=" + whereX +
            ", whereY=" + whereY +
            '}';
    }
}
