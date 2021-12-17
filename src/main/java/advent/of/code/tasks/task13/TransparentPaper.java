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

    public int[][] getPaper() {
        return paper;
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

        paper = new int[maxY + 2][maxX + 2];

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
            .max().orElseThrow() + 1;

        return max % 2 == 0 ? max + 1 : max;
    }

    public void foldByLine(String[] line) {
        int fold = Integer.parseInt(line[1]);
        if (line[0].equals("x")) {
            // for (int i = 0; i < paper.length; i++) {
            //     if (paper[i][fold] == 1) {
            //         System.out.println("# ");
            //     } else {
            //         System.out.println("  ");
            //     }
            // }
            paper = foldLeft(fold);
        } else {
            // for (int i = 0; i < paper[0].length; i++) {
            //     if (paper[fold][i] == 1) {
            //         System.out.print("# ");
            //     } else {
            //         System.out.print("  ");
            //     }
            // }
            // System.out.println();
            paper = foldUp(fold);
        }
        // System.out.println("x: " + paper[0].length + " y: " + paper.length);
        // System.out.println("---------");
    }

    private int[][] foldLeft(int fold) {
        int[][] temp = new int[paper.length][fold];
        // int[][] temp = new int[paper.length][paper[0].length];

        for (int i = 0; i < paper.length; i++) {
            for (int j = 0; j < fold; j++) {
                // System.out.print("i: " + i + " j: " + j + ", ");
                // System.out.print("i: " + i + " j: " + (paper[0].length - j - 1) + " | ");
                // System.out.println(paper[i][j]);
                // System.out.println(paper[i][paper[0].length - j - 1]);
                if (paper[i][j] == 1 || (paper[i][fold + (fold - j)] == 1)) {
                    temp[i][j] = 1;
                }
            }
            // System.out.println();
        }

        return temp;
    }

    private int[][] foldUp(int fold) {
        int[][] temp = new int[fold][paper[0].length];
        // int[][] temp = new int[paper.length][paper[0].length];

        for (int j = 0; j < paper[0].length; j++) {
            for (int i = 0; i < fold; i++) {
                // System.out.println("i: " + i + " j: " + j);
                // System.out.println("i: " + (paper.length - i - 1) + " j: " + j);
                // System.out.println(paper[i][j]);
                // System.out.println(paper[paper.length - i - 1][j]);
                // System.out.println("**********");
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
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 40; j++) {
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
