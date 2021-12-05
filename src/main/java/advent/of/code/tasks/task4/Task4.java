package advent.of.code.tasks.task4;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import advent.of.code.helper.Helper;

public class Task4 {

    public static void main(String[] args) throws IOException {
        String bingoContent = Helper.convertTxtToStringContent("input4.txt");

        List<Integer> drawnNumbers = BingoConverter.createDrawnNumbers(bingoContent);
        List<BingoTable> bingoTables = BingoConverter.createBingoTables(bingoContent);

        System.out.println(firstWinScore(drawnNumbers, bingoTables));
        System.out.println(lastWinScore(drawnNumbers, bingoTables));
    }

    private static int firstWinScore(List<Integer> drawnNumbers, List<BingoTable> bingoTables) {
        for (int drawnNumber : drawnNumbers) {
            for (BingoTable bingoTable : bingoTables) {
                bingoTable.setNumberMarked(drawnNumber);

                if (bingoTable.checkWin()) {
                    return drawnNumber * bingoTable.getSumOffUnmarked();
                }
            }
        }

        return -1;
    }

    private static int lastWinScore(List<Integer> drawnNumbers, List<BingoTable> bingoTables) {
        List<BingoTable> bingoTablesToRemove = new ArrayList<>();
        for (int drawnNumber : drawnNumbers) {
            for (BingoTable bingoTable : bingoTables) {
                bingoTable.setNumberMarked(drawnNumber);

                if (bingoTable.checkWin() && bingoTables.size() > 1) {
                    bingoTablesToRemove.add(bingoTable);
                } else if (bingoTable.checkWin()) {
                    return drawnNumber * bingoTable.getSumOffUnmarked();
                }
            }
            if (!bingoTablesToRemove.isEmpty()) {
                bingoTablesToRemove.forEach(bingoTables::remove);
            }
        }

        return -1;
    }
}
