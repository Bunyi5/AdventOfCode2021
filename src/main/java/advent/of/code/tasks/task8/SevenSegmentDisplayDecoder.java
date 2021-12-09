package advent.of.code.tasks.task8;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import advent.of.code.helper.Helper;

public class SevenSegmentDisplayDecoder {

    Map<String, String[]> resultTable = Map.of(
        "0", new String[] {"0,1,2,4,5,6", ""},
        "1", new String[] {"2,5", ""},
        "2", new String[] {"0,2,3,4,6", ""},
        "3", new String[] {"0,2,3,5,6", ""},
        "4", new String[] {"1,2,3,5", ""},
        "5", new String[] {"0,1,3,5,6", ""},
        "6", new String[] {"0,1,3,4,5,6", ""},
        "7", new String[] {"0,2,5", ""},
        "8", new String[] {"0,1,2,3,4,5,6", ""},
        "9", new String[] {"0,1,2,3,5,6", ""}
    );

    private List<String> resultChars = Arrays.asList("", "", "", "", "", "", "");

    public void setResultChars(List<String> signalPattern) {
        List<String[]> signalPatternArray = signalPattern.stream()
            .sorted(Comparator.comparingInt(String::length))
            .map(signal -> signal.split(""))
            .collect(Collectors.toList());

        // One
        resultChars.set(2, signalPatternArray.get(0)[0]);
        resultChars.set(5, signalPatternArray.get(0)[1]);

        // Seven
        String[] seven = signalPatternArray.get(1);
        seven = Arrays.stream(seven)
            .filter(s -> !resultChars.contains(s))
            .toArray(String[]::new);
        resultChars.set(0, seven[0]);

        // Four
        String[] four = signalPatternArray.get(2);
        four = Arrays.stream(four)
            .filter(s -> !resultChars.contains(s))
            .toArray(String[]::new);
        resultChars.set(1, four[0]);
        resultChars.set(3, four[1]);

        // Eight
        String[] eight = signalPatternArray.get(9);
        eight = Arrays.stream(eight)
            .filter(s -> !resultChars.contains(s))
            .toArray(String[]::new);
        resultChars.set(4, eight[0]);
        resultChars.set(6, eight[1]);

        // Six, Nine, Zero
        List<String[]> sixChars = signalPatternArray.stream()
            .filter(strings -> strings.length == 6)
            .collect(Collectors.toList());

        boolean sixValid = sixChars.stream().anyMatch(sixChar -> !Arrays.asList(sixChar).contains(resultChars.get(2)));
        if (!sixValid) {
            Collections.swap(resultChars, 2, 5);
        }

        boolean nineValid = sixChars.stream().anyMatch(sixChar -> !Arrays.asList(sixChar).contains(resultChars.get(4)));
        if (!nineValid) {
            Collections.swap(resultChars, 4, 6);
        }

        boolean zeroValid = sixChars.stream().anyMatch(sixChar -> !Arrays.asList(sixChar).contains(resultChars.get(3)));
        if (!zeroValid) {
            Collections.swap(resultChars, 1, 3);
        }
    }

    public void fillResultTable() {
        for (String[] value : resultTable.values()) {
            String[] split = value[0].split(Helper.COMMA);
            String resultCharSequence = "";
            for (String s : split) {
                resultCharSequence = resultCharSequence.concat(resultChars.get(Integer.parseInt(s)));
            }
            char[] resultCharSequenceArray = resultCharSequence.toCharArray();
            Arrays.sort(resultCharSequenceArray);

            Array.set(value, 1, new String(resultCharSequenceArray));
        }
    }

    public int convertOutputValuesToInt(List<String> outputValues) {
        String digits = "";

        for (String outputValue : outputValues) {
            char[] outPutValueArray = outputValue.toCharArray();
            Arrays.sort(outPutValueArray);

            for (Map.Entry<String, String[]> stringEntry : resultTable.entrySet()) {
                if (stringEntry.getValue()[1].equals(new String(outPutValueArray))) {
                    digits = digits.concat(stringEntry.getKey());
                    break;
                }
            }
        }

        return Integer.parseInt(digits);
    }
}
