package advent.of.code.tasks.task14;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import advent.of.code.helper.Helper;

public class Polymer {

    private HashMap<String, Long> template = new HashMap<>();
    private final Map<String, String[]> rules = new HashMap<>();
    private char lastChar;

    public Polymer(String polymerContent) {
        String[] polymerArray = polymerContent.split("(" + Helper.LINE_SEPARATOR + "){2}");

        fillRulesAndTemplate(polymerArray);
    }

    private void fillRulesAndTemplate(String[] polymerArray) {
        Arrays.stream(polymerArray[1].split(Helper.LINE_SEPARATOR))
            .map(s -> s.split(Helper.WHITESPACE + Helper.ARROW + Helper.WHITESPACE))
            .forEach(strings -> {
                template.put(strings[0], 0L);
                rules.put(strings[0], new String[] {strings[0].charAt(0) + strings[1], strings[1] + strings[0].charAt(1)});
            });

        char[] charArray = polymerArray[0].toCharArray();
        for (int i = 0; i < charArray.length - 1; i++) {
            String rule = "" + charArray[i] + charArray[i + 1];
            template.put(rule, 1L);
        }

        lastChar = charArray[charArray.length - 1];
    }

    public void doStep(int stepTimes) {
        for (int i = 0; i < stepTimes; i++) {
            HashMap<String, Long> temp = (HashMap<String, Long>) template.clone();

            for (Map.Entry<String, Long> stringIntegerEntry : template.entrySet()) {
                if (stringIntegerEntry.getValue() != 0) {
                    String[] strings = rules.get(stringIntegerEntry.getKey());

                    for (String string : strings) {
                        temp.put(string, temp.get(string) + template.get(stringIntegerEntry.getKey()));
                    }

                    temp.put(stringIntegerEntry.getKey(), temp.get(stringIntegerEntry.getKey()) - template.get(stringIntegerEntry.getKey()));
                }
            }
            template = temp;
        }
    }

    public long quantityOfTheMostAndLeastCommon() {
        List<Character> possibleCharacters = template.keySet().stream().map(s -> s.charAt(0)).distinct().collect(Collectors.toList());

        long max = 0;
        long min = Long.MAX_VALUE;

        for (int i = 0; i < possibleCharacters.size(); i++) {
            int finalI = i;
            long sum = template.entrySet().stream()
                .filter(stringIntegerEntry -> stringIntegerEntry.getKey().charAt(0) == possibleCharacters.get(finalI))
                .map(Map.Entry::getValue)
                .mapToLong(Long::valueOf)
                .sum();

            if (possibleCharacters.get(finalI) == lastChar) {
                sum++;
            }

            if (sum > max) {
                max = sum;
            }

            if (sum < min) {
                min = sum;
            }
        }

        return max - min;
    }
}
