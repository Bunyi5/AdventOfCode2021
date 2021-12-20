package advent.of.code.tasks.task15;

import java.util.Objects;

public class RiskPoint {

    private int x;
    private int y;
    private int value;

    public RiskPoint(int x, int y, int value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RiskPoint riskPoint = (RiskPoint) o;
        return x == riskPoint.x && y == riskPoint.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "RiskPoint{" +
            "x=" + x +
            ", y=" + y +
            ", value=" + value +
            '}';
    }
}
