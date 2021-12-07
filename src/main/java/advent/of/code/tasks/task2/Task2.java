package advent.of.code.tasks.task2;

import java.io.IOException;
import java.util.List;

import advent.of.code.helper.Helper;

public class Task2 {

    public static void main(String[] args) throws IOException {
        List<String> commandList = Helper.convertTxtToStringList("input2.txt", Helper.LINE_SEPARATOR);

        System.out.println("Final position multiplied by depth: " + calculateFinalPositionAndDepth(commandList));
        System.out.println("Final position multiplied by depth with aim: " + calculateFinalPositionAndDepthWithAim(commandList));
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
