package tasks;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

import helper.Helper;

public class Task3 {

    public static void main(String[] args) throws FileNotFoundException {
        List<String> binaryList = Helper.convertTxtToStringList("input3.txt");

        System.out.println(calculatePowerConsumption(binaryList));

        int oxygen = calculateLifeSupportRating(binaryList, LifeSupportType.OXYGEN_GENERATOR);
        int co2 = calculateLifeSupportRating(binaryList, LifeSupportType.CO2_SCRUBBER);
        System.out.println(oxygen * co2);
    }

    private static int calculatePowerConsumption(List<String> binaryList) {
        int binaryNumberLength = binaryList.get(0).length();
        StringBuilder gammaRateString = new StringBuilder();
        StringBuilder epsilonRateString = new StringBuilder();

        for (int i = 0; i < binaryNumberLength; i++) {
            int ones = 0;
            int zeros = 0;

            for (String binary : binaryList) {
                char num = binary.charAt(i);

                if (num == '1') {
                    ones++;
                } else {
                    zeros++;
                }
            }

            if (ones > zeros) {
                gammaRateString.append(1);
                epsilonRateString.append(0);
            } else {
                gammaRateString.append(0);
                epsilonRateString.append(1);
            }
        }

        int gammaRate = Integer.parseInt(gammaRateString.toString(), 2);
        int epsilonRate = Integer.parseInt(epsilonRateString.toString(), 2);

        return gammaRate * epsilonRate;
    }

    private static int calculateLifeSupportRating(List<String> binaryList, LifeSupportType lifeSupportType) {
        int binaryNumberLength = binaryList.get(0).length();

        for (int i = 0; i < binaryNumberLength; i++) {
            int ones = 0;
            int zeros = 0;

            for (String binary : binaryList) {
                int num = binary.charAt(i);

                if (num == '1') {
                    ones++;
                } else {
                    zeros++;
                }
            }

            int finalI = i;
            char filterParam;
            if (lifeSupportType.apply(ones, zeros)) {
                filterParam = '1';
            } else {
                filterParam = '0';
            }

            binaryList = binaryList.stream()
                .filter(binary -> binary.charAt(finalI) == filterParam)
                .collect(Collectors.toList());

            if (binaryList.size() == 1) {
                break;
            }
        }

        return Integer.parseInt(binaryList.get(0), 2);
    }

    private enum LifeSupportType {
        OXYGEN_GENERATOR {
            @Override
            public boolean apply(int x1, int x2) {
                return x1 >= x2;
            }
        },
        CO2_SCRUBBER {
            @Override
            public boolean apply(int x1, int x2) {
                return x1 < x2;
            }
        };

        public abstract boolean apply(int x1, int x2);
    }
}
