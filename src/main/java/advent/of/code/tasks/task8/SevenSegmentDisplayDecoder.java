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

    private final List<String> resultChars = Arrays.asList("", "", "", "", "", "", "");

    private final int[] swapAndFillPairTwoFive = {2, 5};
    private final int[] swapAndFillPairOneThree = {1, 3};
    private final int[] swapAndFillPairFourSix = {4, 6};

    public void setResultChars(List<String> signalPattern) {
        List<String[]> signalPatternArray = signalPattern.stream()
            .sorted(Comparator.comparingInt(String::length))
            .map(signal -> signal.split(Helper.NONE))
            .collect(Collectors.toList());

        // One
        fillResultChars(signalPatternArray, 0, swapAndFillPairTwoFive);
        // Seven
        fillResultChars(signalPatternArray, 1, new int[] {0});
        // Four
        fillResultChars(signalPatternArray, 2, swapAndFillPairOneThree);
        // Eight
        fillResultChars(signalPatternArray, 9, swapAndFillPairFourSix);

        List<String[]> sixChars = signalPatternArray.stream()
            .filter(strings -> strings.length == 6)
            .collect(Collectors.toList());

        // Six
        swapIfWrongInResultChars(sixChars, 2, swapAndFillPairTwoFive);
        // Zero
        swapIfWrongInResultChars(sixChars, 3, swapAndFillPairOneThree);
        // Nine
        swapIfWrongInResultChars(sixChars, 4, swapAndFillPairFourSix);
    }

    private void fillResultChars(List<String[]> signalPatternArray, int index, int[] setIndexes) {
        String[] number = signalPatternArray.get(index);
        number = Arrays.stream(number)
            .filter(s -> !resultChars.contains(s))
            .toArray(String[]::new);

        for (int i = 0; i < setIndexes.length; i++) {
            resultChars.set(setIndexes[i], number[i]);
        }
    }

    private void swapIfWrongInResultChars(List<String[]> sixChars, int index, int[] swapIndexes) {
        if (sixChars.stream().allMatch(sixChar -> Arrays.asList(sixChar).contains(resultChars.get(index)))) {
            Collections.swap(resultChars, swapIndexes[0], swapIndexes[1]);
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
