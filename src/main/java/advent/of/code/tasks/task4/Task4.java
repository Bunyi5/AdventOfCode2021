package advent.of.code.tasks.task4;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import advent.of.code.helper.Helper;
import advent.of.code.helper.RunType;

public class Task4 {

    private static final int TASK_NUMBER = 4;

    public static void main(String[] args) throws IOException {
        runMain(RunType.TEST);
        runMain(RunType.REAL);
    }

    private static void runMain(RunType runType) throws IOException {
        System.out.println(runType);
        String bingoContent = Helper.convertTxtToStringContent("input" + TASK_NUMBER + runType + ".txt");

        List<Integer> drawnNumbers = BingoConverter.createDrawnNumbers(bingoContent);
        List<BingoTable> bingoTables = BingoConverter.createBingoTables(bingoContent);

        int firstWinScore = firstWinScore(drawnNumbers, bingoTables);
        System.out.println("First win score: " + firstWinScore);
        Helper.assertResults(firstWinScore, TASK_NUMBER, 1, runType);

        int lastWinScore = lastWinScore(drawnNumbers, bingoTables);
        System.out.println("Last win score: " + lastWinScore);
        Helper.assertResults(lastWinScore, TASK_NUMBER, 2, runType);
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
