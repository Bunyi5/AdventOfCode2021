package advent.of.code.helper;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Helper {

    public static Scanner readFileWithScanner(String fileName) throws FileNotFoundException {
        File file = new File("src/main/resources/" + fileName);
        return new Scanner(file);
    }

    public static List<Integer> convertTxtToIntList(String fileName) throws FileNotFoundException {
        Scanner sc = readFileWithScanner(fileName);
        List<Integer> intList = new ArrayList<>();

        while (sc.hasNextLine()) {
            intList.add(Integer.parseInt(sc.nextLine()));
        }

        return intList;
    }

    public static List<String> convertTxtToStringList(String fileName) throws FileNotFoundException {
        Scanner sc = readFileWithScanner(fileName);
        List<String> stringList = new ArrayList<>();

        while (sc.hasNextLine()) {
            stringList.add(sc.nextLine());
        }

        return stringList;
    }
}
