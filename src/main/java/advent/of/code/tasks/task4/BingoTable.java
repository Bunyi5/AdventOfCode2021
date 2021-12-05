package advent.of.code.tasks.task4;

import java.util.Arrays;

public class BingoTable {

    private BingoNumber[][] table;
    private boolean alreadyWon;

    public BingoTable(BingoNumber[][] table) {
        this.table = table;
        this.alreadyWon = false;
    }

    public BingoNumber[][] getTable() {
        return table;
    }

    public void setTable(BingoNumber[][] table) {
        this.table = table;
    }

    public boolean isAlreadyWon() {
        return alreadyWon;
    }

    public void setAlreadyWon(boolean alreadyWon) {
        this.alreadyWon = alreadyWon;
    }

    public void setNumberMarked(int drawnNumber) {
        for (BingoNumber[] bingoNumbers : table) {
            for (BingoNumber bingoNumber : bingoNumbers) {
                if (bingoNumber.getNumber() == drawnNumber) {
                    bingoNumber.setMarked(true);
                }
            }
        }
    }

    public int getSumOffUnmarked() {
        int sum = 0;
        for (BingoNumber[] bingoNumbers : table) {
            for (BingoNumber bingoNumber : bingoNumbers) {
                if (!bingoNumber.isMarked()) {
                    sum += bingoNumber.getNumber();
                }
            }
        }

        return sum;
    }

    public boolean checkWin() {
        int row = 0;
        int column = 0;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (table[i][j].isMarked()) {
                    row++;
                }
                if (table[j][i].isMarked()) {
                    column++;
                }
            }
            if (row == 5 || column == 5) {
                return true;
            }

            row = 0;
            column = 0;
        }

        return false;
    }

    @Override
    public String toString() {
        return "BingoTable{" +
            "table=" + Arrays.toString(table) +
            '}';
    }
}
