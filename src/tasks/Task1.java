package tasks;

import java.io.FileNotFoundException;
import java.util.List;

import helper.Helper;

public class Task1 {

    public static void main(String[] args) throws FileNotFoundException {
        List<Integer> intList = Helper.convertTxtToIntList("input1.txt");

        System.out.println("One measurement: " + doOneMeasurement(intList));
        System.out.println("Three measurement: " + doThreeMeasurement(intList));
    }

    private static int doOneMeasurement(List<Integer> intList) {
        int previous = intList.get(0);
        int next;
        int counter = 0;

        for (Integer integer : intList) {
            next = integer;

            if (next > previous) {
                counter++;
            }

            previous = next;
        }

        return counter;
    }

    private static int doThreeMeasurement(List<Integer> intList) {
        int previous = intList.get(0) + intList.get(1) + intList.get(2);
        int next;
        int counter = 0;

        for (int i = 1; i < intList.size() - 2; i++) {
            next = intList.get(i) + intList.get(i + 1) + intList.get(i + 2);

            if (next > previous) {
                counter++;
            }

            previous = next;
        }

        return counter;
    }

}
