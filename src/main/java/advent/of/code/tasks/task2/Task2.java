package advent.of.code.tasks.task2;

import java.io.IOException;
import java.util.List;

import advent.of.code.helper.Helper;
import advent.of.code.helper.RunType;

public class Task2 {

    private static final int TASK_NUMBER = 2;

    public static void main(String[] args) throws IOException {
        runMain(RunType.TEST);
        runMain(RunType.REAL);
    }

    private static void runMain(RunType runType) throws IOException {
        System.out.println(runType);
        List<String> commandList = Helper.convertTxtToStringList(TASK_NUMBER, runType, Helper.LINE_SEPARATOR);

        int finalPositionAndDepth = calculateFinalPositionAndDepth(commandList);
        System.out.println("Final position multiplied by depth: " + finalPositionAndDepth);
        Helper.assertResults(finalPositionAndDepth, TASK_NUMBER, 1, runType);

        int finalPositionAndDepthWithAim = calculateFinalPositionAndDepthWithAim(commandList);
        System.out.println("Final position multiplied by depth with aim: " + finalPositionAndDepthWithAim);
        Helper.assertResults(finalPositionAndDepthWithAim, TASK_NUMBER, 2, runType);
    }

    private static int calculateFinalPositionAndDepth(List<String> commandList) {
        int position = 0;
        int depth = 0;

        for (String command : commandList) {
            String[] directionAndAmount = command.split(Helper.WHITESPACE, 2);
            String direction = directionAndAmount[0];
            int amount = Integer.parseInt(directionAndAmount[1]);

            switch (direction) {
                case "forward" -> position += amount;
                case "down" -> depth += amount;
                case "up" -> depth -= amount;
            }
        }

        return position * depth;
    }

    private static int calculateFinalPositionAndDepthWithAim(List<String> commandList) {
        int position = 0;
        int depth = 0;
        int aim = 0;

        for (String command : commandList) {
            String[] directionAndAmount = command.split(Helper.WHITESPACE, 2);
            String direction = directionAndAmount[0];
            int amount = Integer.parseInt(directionAndAmount[1]);

            switch (direction) {
                case "forward" -> {
                    position += amount;
                    depth += aim * amount;
                }
                case "down" -> aim += amount;
                case "up" -> aim -= amount;
            }
        }

        return position * depth;
    }

}
