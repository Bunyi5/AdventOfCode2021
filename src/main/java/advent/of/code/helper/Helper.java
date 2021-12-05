package advent.of.code.helper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Helper {

    private static final String RESOURCE_PATH = "src/main/resources/";

    public static Scanner readFileWithScanner(String fileName) throws FileNotFoundException {
        File file = new File(RESOURCE_PATH + fileName);
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

    public static String convertTxtToStringContent(String fileName) throws IOException {
        Path path = Path.of(RESOURCE_PATH, fileName);
        return Files.readString(path);
    }
}
