package advent.of.code.tasks.task4;

public class BingoNumber {

    private boolean marked;
    private int number;

    public BingoNumber(boolean marked, int number) {
        this.marked = marked;
        this.number = number;
    }

    public boolean isMarked() {
        return marked;
    }

    public void setMarked(boolean marked) {
        this.marked = marked;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "BingoNumber{" +
            "marked=" + marked +
            ", number=" + number +
            '}';
    }
}
