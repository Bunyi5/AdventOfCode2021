package advent.of.code.tasks.task13;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import advent.of.code.helper.Helper;

public class TransparentPaper {

    private int[][] paper;
    private List<String[]> instructions;

    public TransparentPaper(String paperContext) {
        String[] paperArray = paperContext.split("(" + Helper.LINE_SEPARATOR + "){2}");

        fillPaper(paperArray);
        fillInstructions(paperArray);
    }

    public List<String[]> getInstructions() {
        return instructions;
    }

    private void fillPaper(String[] paperArray) {
        List<int[]> markedPoints = Arrays.stream(paperArray[0].split(Helper.LINE_SEPARATOR))
            .map(s -> s.split(Helper.COMMA))
            .map(strings -> Arrays.stream(strings).mapToInt(Integer::parseInt).toArray())
            .collect(Collectors.toList());

        int maxX = getMaxXY(markedPoints, 0);
        int maxY = getMaxXY(markedPoints, 1);

        paper = new int[maxY][maxX];

        markedPoints.forEach(ints -> paper[ints[1]][ints[0]]++);
    }

    private void fillInstructions(String[] paperArray) {
        instructions = Arrays.stream(paperArray[1].split(Helper.LINE_SEPARATOR))
            .map(s -> s.substring(11).split(Helper.EQUAL))
            .collect(Collectors.toList());
    }

    private int getMaxXY(List<int[]> markedPoints, int xy) {
        int max = markedPoints.stream()
            .mapToInt(ints -> ints[xy])
            .max().orElseThrow() + 3;

        return max % 2 == 0 ? max + 1 : max;
    }

    public void foldByLine(String[] line) {
        int fold = Integer.parseInt(line[1]);
        if (line[0].equals("x")) {
            paper = foldLeft(fold);
        } else {
            paper = foldUp(fold);
        }
    }

    private int[][] foldLeft(int fold) {
        int[][] temp = new int[paper.length][fold];

        for (int i = 0; i < paper.length; i++) {
            for (int j = 0; j < fold; j++) {
                if (paper[i][j] == 1 || (paper[i][fold + (fold - j)] == 1)) {
                    temp[i][j] = 1;
                }
            }
        }

        return temp;
    }

    private int[][] foldUp(int fold) {
        int[][] temp = new int[fold][paper[0].length];

        for (int j = 0; j < paper[0].length; j++) {
            for (int i = 0; i < fold; i++) {
                if (paper[i][j] == 1 || paper[fold + (fold - i)][j] == 1) {
                    temp[i][j] = 1;
                }
            }
        }

        return temp;
    }

    public int countDots() {
        return Arrays.stream(paper)
            .flatMapToInt(Arrays::stream)
            .sum();
    }

    public void printPaper() {
        for (int i = 0; i < paper.length; i++) {
            for (int j = 0; j < paper[0].length; j++) {
                if (paper[i][j] == 1) {
                    System.out.print("% ");
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
    }
}
