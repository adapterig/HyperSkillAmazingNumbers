package numbers;

import java.util.*;

public class NumbersProperties {
    private NumbersProperties() {
    }

    public static final String[] PROPERTIES = {"EVEN", "ODD", "BUZZ", "DUCK", "PALINDROMIC", "GAPFUL", "SPY", "SQUARE",
            "SUNNY", "JUMPING", "HAPPY", "SAD"};
    public static final String[][] MUTUALLY_EXCLUSIVE_PROPERTIES = {{"EVEN", "ODD"}, {"DUCK", "SPY"},
            {"SQUARE", "SUNNY"}, {"-EVEN", "-ODD"}, {"EVEN", "-EVEN"}, {"ODD", "-ODD"}, {"BUZZ", "-BUZZ"},
            {"DUCK", "-DUCK"}, {"PALINDROMIC", "-PALINDROMIC"}, {"GAPFUL", "-GAPFUL"}, {"SPY", "-SPY"},
            {"SQUARE", "-SQUARE"}, {"SUNNY", "-SUNNY"}, {"JUMPING", "-JUMPING"}, {"HAPPY", "-HAPPY"}, {"SAD", "-SAD"}};

    public static boolean checkProperty(long number, String property) {
        switch (property) {
            case "EVEN":
                return checkIsEven(number);
            case "ODD":
                return checkIsOdd(number);
            case "BUZZ":
                return checkIsBuzz(number);
            case "DUCK":
                return checkIsDuck(number);
            case "PALINDROMIC":
                return checkIsPalindromic(number);
            case "GAPFUL":
                return checkIsGapful(number);
            case "SPY":
                return checkIsSpy(number);
            case "SQUARE":
                return checkIsSquare(number);
            case "SUNNY":
                return checkIsSunny(number);
            case "JUMPING":
                return checkIsJumping(number);
            case "HAPPY":
                return checkIsHappy(number);
            case "-SAD":
                return !checkIsSad(number);
            case "SAD":
                return checkIsSad(number);
            case "-HAPPY":
                return !checkIsHappy(number);
            case "-EVEN":
                return !checkIsEven(number);
            case "-ODD":
                return !checkIsOdd(number);
            case "-BUZZ":
                return !checkIsBuzz(number);
            case "-DUCK":
                return !checkIsDuck(number);
            case "-PALINDROMIC":
                return !checkIsPalindromic(number);
            case "-GAPFUL":
                return !checkIsGapful(number);
            case "-SPY":
                return !checkIsSpy(number);
            case "-SQUARE":
                return !checkIsSquare(number);
            case "-SUNNY":
                return !checkIsSunny(number);
            case "-JUMPING":
                return !checkIsJumping(number);

            default:
                throw new RuntimeException("Wrong Properties in switch operator");
        }
    }

    public static boolean checkIsEven(long number) {
        if (number % 2 == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkIsOdd(long number) {
        return !checkIsEven(number);
    }

    public static boolean checkIsBuzz(long number) {
        if (number % 7 == 0 || number - (number / 10) * 10 == 7) {
            return true;
        } else {
            return false;
        }

    }

    public static boolean checkIsDuck(long number) {
        String numberString = String.valueOf(number);
        for (int i = 0; i < numberString.length(); i++) {
            if (numberString.charAt(i) == '0') {
                return true;
            }
        }
        return false;

    }

    public static boolean checkIsPalindromic(long number) {
        String numberString = String.valueOf(number);
        for (int i = 0; i < numberString.length() / 2; i++) {
            if (numberString.charAt(i) != numberString.charAt(numberString.length() - i - 1)) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkIsGapful(long number) {
        String numberString = String.valueOf(number);
        if (numberString.length() >= 3 &&
                number % Integer.parseInt("" + numberString.charAt(0) + numberString.charAt(numberString.length() - 1)) == 0) {
            return true;
        }
        return false;
    }

    public static boolean checkIsSpy(long number) {
        String numberString = String.valueOf(number);
        if (numberString.length() == 1) {
            return true;
        }
        long sum = 0;
        long product = 1;
        int digit;
        for (int i = 0; i < numberString.length(); i++) {
            digit = Integer.parseInt("" + numberString.charAt(i));
            sum += digit;
            product *= digit;
        }
        return sum == product;
    }

    public static boolean checkIsSquare(long number) {
        if ((long) Math.sqrt(number) * (long) Math.sqrt(number) == number) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkIsSunny(long number) {
        return checkIsSquare(number + 1);
    }

    public static boolean checkIsJumping(long number) {
        String stringNumber = String.valueOf(number);
        for (int i = 0; i < stringNumber.length() - 1; i++) {
            if (Math.abs(stringNumber.charAt(i) - stringNumber.charAt(i + 1)) != 1) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkIsHappy(long number) {
        String stringNumber = String.valueOf(number);
        Set<Long> setOfNumbers = new HashSet<>();
        if (number == 1 || number == 7) {
            return true;
        }
        while (true) {
            long sumOfDigitsSquares = 0;
            for (int i = 0; i < stringNumber.length(); i++) {
                int digit = Integer.parseInt(stringNumber.substring(i, i + 1));
                sumOfDigitsSquares += (long) digit * digit;

            }

            if (sumOfDigitsSquares == 1) {
                return true;
            } else if (!setOfNumbers.add(sumOfDigitsSquares)) {
                return false;
            }
            stringNumber = String.valueOf(sumOfDigitsSquares);
        }
    }

    public static boolean checkIsSad(long number) {
        return !checkIsHappy(number);
    }


    public static boolean checkIsMutuallyExclusiveProperties(List<String> properties) {
        List<String> mutuallyExclusiveProperties = new ArrayList<>();
        for (String[] s : MUTUALLY_EXCLUSIVE_PROPERTIES) {
            if (properties.containsAll(Arrays.stream(s).toList())) {
                mutuallyExclusiveProperties.addAll(Arrays.stream(s).toList());
            }
        }
        if (mutuallyExclusiveProperties.isEmpty()) {
            return false;
        } else {
            System.out.println("The request contains mutually exclusive properties: " + mutuallyExclusiveProperties + "\n" +
                    "There are no numbers with these properties.");
            return true;
        }
    }
}

